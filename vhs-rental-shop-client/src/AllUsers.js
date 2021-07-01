import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import { getAllUsers } from "./api/apiCalls";
import User from "./User";

import "./AllUsers.css";

function AllUsers() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        getAllUsers().then((data) => setUsers(data));
    }, []);

    return (
        <div className="all-users-main">
            <h1 className="all-users-header-text">All users</h1>
            <div className="all-users-container">
                {users.map((user) => (
                    <Link
                        className="all-users-link"
                        key={user.username}
                        to={"/" + user.username + "/my-rentals"}
                    >
                        <User {...user} />
                    </Link>
                ))}
            </div>
        </div>
    );
}

export default AllUsers;
