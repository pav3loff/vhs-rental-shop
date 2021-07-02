import { useEffect, useState } from "react";

import { Button } from "@material-ui/core";

import { deleteRental, getRental, returnRental } from "./api/apiCalls";

import "./Rental.css";

function Rental(props) {
    const id = props.match.params.id;

    const [rental, setRental] = useState({
        vhs: {},
        dateRented: "",
        dueDate: "",
        dateReturned: "",
        lateFee: "",
    });
    const [showFee, setShowFee] = useState(false);
    const [isDisabled, setDisabled] = useState(false);

    useEffect(() => {
        getRental(id).then((data) => setRental(data));
    }, [id]);

    function handleReturn() {
        returnRental(id).then((data) => {
            if (data.isSuccessful) {
                setRental({
                    ...rental,
                    dateReturned: data.info.dateReturned,
                    lateFee: data.info.lateFee,
                });

                setShowFee(true);

                setDisabled(true);

                deleteRental(id);
            }
        });
    }

    return (
        <div className="rental-main">
            {rental.vhs && (
                <>
                    <h1>{rental.vhs.title}</h1>
                    <h2>{rental.vhs.year}</h2>
                    <p>{rental.vhs.plot}</p>
                </>
            )}
            {rental.dateRented && (
                <p>
                    Rented:{" "}
                    {rental.dateRented.split(" ")[0].replaceAll("-", ". ")}
                </p>
            )}
            {rental.dueDate && (
                <p>Due: {rental.dueDate.split(" ")[0].replaceAll("-", ". ")}</p>
            )}
            {props.isAdmin && (
                <Button
                    className="rental-btn"
                    onClick={handleReturn}
                    disabled={isDisabled}
                >
                    Return
                </Button>
            )}
            {showFee && <p>Late fee: {rental.lateFee} EUR</p>}
        </div>
    );
}

export default Rental;
