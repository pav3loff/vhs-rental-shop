import { useEffect } from "react";
import { useState } from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";

import Navbar from "./Navbar";
import Login from "./Login";
import Home from "./Home";
import Vhs from "./Vhs";
import MyRentals from "./MyRentals";

function App() {
    const [isLoggedIn, setLoggedIn] = useState(false);

    useEffect(() => {
        if (localStorage.getItem("loggedUserUsername")) {
            setLoggedIn(true);
        }
    }, []);

    return (
        <BrowserRouter>
            <Navbar isLoggedIn={isLoggedIn} setLoggedIn={setLoggedIn} />

            <Switch>
                <Route
                    exact
                    path="/"
                    render={() => <Home isLoggedIn={isLoggedIn} />}
                />
                <Route
                    exact
                    path="/login"
                    render={() => (
                        <Login
                            isLoggedIn={isLoggedIn}
                            setLoggedIn={setLoggedIn}
                        />
                    )}
                />
                <Route
                    exact
                    path="/vhses/:id"
                    render={(props) => <Vhs {...props} />}
                />
                <Route exact path="/my-rentals" render={() => <MyRentals />} />
            </Switch>
        </BrowserRouter>
    );
}

export default App;
