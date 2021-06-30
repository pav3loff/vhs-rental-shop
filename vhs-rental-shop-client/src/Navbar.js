import { Link } from "react-router-dom";

import { AppBar, Toolbar, Button } from "@material-ui/core";

import "./Navbar.css";

function Navbar(props) {
    function handleLogout() {
        localStorage.removeItem("loggedUserUsername");
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
                            <Link className="link" to="/my-rentals">
                                <Button className="nav-btn">My rentals</Button>
                            </Link>
                            <Button className="nav-btn" onClick={handleLogout}>
                                Logout
                            </Button>
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
