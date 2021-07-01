import { Link } from "react-router-dom";

import { AppBar, Toolbar, Button } from "@material-ui/core";

import "./Navbar.css";
import { logout } from "./api/apiCalls";

function Navbar(props) {
    function handleLogout() {
        logout();
        props.setLoggedIn(false);
    }

    return (
        <AppBar className="nav" position="static">
            <Toolbar>
                <div className="leftside">
                    <Link className="link" to="/">
                        <Button className="nav-btn">Home</Button>
                    </Link>
                </div>
                <div className="rightside">
                    {props.isLoggedIn ? (
                        <>
                            {props.userInfo.isAdmin ? (
                                <Link className="link" to="/all-users">
                                    <Button className="nav-btn">Users</Button>
                                </Link>
                            ) : (
                                <Link
                                    className="link"
                                    to={
                                        "/" +
                                        props.userInfo.username +
                                        "/my-rentals"
                                    }
                                >
                                    <Button className="nav-btn">Rentals</Button>
                                </Link>
                            )}
                            <Link
                                className="link"
                                to="/"
                                onClick={handleLogout}
                            >
                                <Button className="nav-btn">Logout</Button>
                            </Link>
                        </>
                    ) : (
                        <>
                            <Link className="link" to="/login">
                                <Button className="nav-btn">Login</Button>
                            </Link>
                            <Link className="link" to="/registration">
                                <Button className="nav-btn">
                                    Registration
                                </Button>
                            </Link>
                        </>
                    )}
                </div>
            </Toolbar>
        </AppBar>
    );
}

export default Navbar;
