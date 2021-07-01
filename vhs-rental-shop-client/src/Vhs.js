import { useEffect, useState } from "react";

import { Button, Modal, Snackbar } from "@material-ui/core";

import { addRental, getAllUsers, getVhs, isVhsAvailable } from "./api/apiCalls";

import "./Vhs.css";

function Vhs(props) {
    const id = props.match.params.id;

    const [vhs, setVhs] = useState({ title: "", year: "", plot: "" });
    const [isAvailable, setAvailable] = useState(true);
    const [isAvailableLoading, setAvailableLoading] = useState(true);
    const [isModalOpen, setModalOpen] = useState(false);
    const [isSnackbarOpen, setSnackbarOpen] = useState(false);
    const [users, setUsers] = useState([]);
    const [areUsersLoading, setUsersLoading] = useState(true);

    useEffect(() => {
        getVhs(id).then((data) => setVhs(data));
    }, [id]);

    useEffect(() => {
        isVhsAvailable(id).then((data) => {
            setAvailable(data);

            setAvailableLoading(false);
        });
    }, [id]);

    function handleClick() {
        getAllUsers().then((data) => {
            setUsers(data);

            setUsersLoading(false);
        });

        setModalOpen(true);
    }

    function handleRent(username) {
        addRental(id, username).then((data) => {
            setAvailable(!data);
            setSnackbarOpen(true);
            setModalOpen(false);
        });
    }

    return (
        <div className="vhs-main">
            <h1>{vhs.title}</h1>
            <h2>{vhs.year}</h2>
            <p>{vhs.plot}</p>
            {props.isLoggedIn &&
                !isAvailableLoading &&
                (isAvailable ? (
                    props.isAdmin ? (
                        <>
                            <Button className="vhs-btn" onClick={handleClick}>
                                Rent
                            </Button>
                            <Modal
                                open={!areUsersLoading && isModalOpen}
                                onClose={() => setModalOpen(false)}
                            >
                                <div className="vhs-modal">
                                    <h3 className="vhs-modal-header-text">
                                        Rent to:
                                    </h3>
                                    {users.map((user) => (
                                        <div
                                            className="vhs-modal-user"
                                            key={user.username}
                                            onClick={() =>
                                                handleRent(user.username)
                                            }
                                        >
                                            <h4>{user.username}</h4>
                                        </div>
                                    ))}
                                </div>
                            </Modal>
                        </>
                    ) : (
                        <p>Currently available!</p>
                    )
                ) : (
                    <p>Currently unavailable!</p>
                ))}
            <Snackbar
                open={isSnackbarOpen}
                autoHideDuration={2000}
                onClose={() => setSnackbarOpen(false)}
                message="Rental added!"
            />
        </div>
    );
}

export default Vhs;
