import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import { getUser } from "./api/apiCalls";
import RentalCompact from "./RentalCompact";

import "./MyRentals.css";

function MyRentals(props) {
    const [user, setUser] = useState({
        username: "",
        firstName: "",
        lastName: "",
        rentals: [],
    });

    useEffect(() => {
        const username = props.match.params.username;

        getUser(username).then((data) => setUser(data));
    }, [props.match.params.username]);

    return (
        <div className="rentals-main">
            {user.firstName && user.lastName && (
                <h1 className="rentals-h1">
                    {user.firstName} {user.lastName}'s rentals
                </h1>
            )}
            {user.username && <h3>({user.username})</h3>}
            <div className="rentals-container">
                {user.rentals.map((rental) => (
                    <Link
                        className="rentals-link"
                        key={rental.id}
                        to={"/my-rentals/" + rental.id}
                    >
                        <RentalCompact {...rental} />
                    </Link>
                ))}
            </div>
        </div>
    );
}

export default MyRentals;
