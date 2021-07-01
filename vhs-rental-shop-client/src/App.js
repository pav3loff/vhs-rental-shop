import { useEffect } from "react";
import { useState } from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";

import Navbar from "./Navbar";
import Login from "./Login";
import Home from "./Home";
import Vhs from "./Vhs";
import MyRentals from "./MyRentals";
import Rental from "./Rental";
import { getUserInfo } from "./api/apiCalls";
import AllUsers from "./AllUsers";

function App() {
    const [isLoading, setLoading] = useState(true);
    const [isLoggedIn, setLoggedIn] = useState(false);
    const [loggedUserInfo, setLoggedUserInfo] = useState({
        username: "",
        isAdmin: false,
    });

    useEffect(() => {
        getUserInfo()
            .then((response) => {
                if (response.isSuccessful) {
                    setLoggedUserInfo(response.userInfo);
                    setLoggedIn(true);
                } else {
                    setLoggedIn(false);
                }

                setLoading(false);
            })
            .catch(() => {});
    }, [isLoggedIn]);

    return (
        !isLoading && (
            <BrowserRouter>
                <Navbar
                    isLoggedIn={isLoggedIn}
                    setLoggedIn={setLoggedIn}
                    userInfo={loggedUserInfo}
                />

                <Switch>
                    <Route exact path="/" render={() => <Home />} />
                    <Route
                        exact
                        path="/login"
                        render={() => (
                            <Login
                                isLoggedIn={isLoggedIn}
                                setLoggedIn={setLoggedIn}
                                setLoggedUserInfo={setLoggedUserInfo}
                            />
                        )}
                    />
                    <Route
                        exact
                        path="/vhses/:id"
                        render={(props) => (
                            <Vhs
                                {...props}
                                isLoggedIn={isLoggedIn}
                                isAdmin={loggedUserInfo.isAdmin}
                            />
                        )}
                    />
                    <Route
                        exact
                        path="/:username/my-rentals"
                        render={(props) => (
                            <MyRentals userInfo={loggedUserInfo} {...props} />
                        )}
                    />
                    <Route
                        exact
                        path="/my-rentals/:id"
                        render={(props) => (
                            <Rental
                                {...props}
                                isAdmin={loggedUserInfo.isAdmin}
                            />
                        )}
                    />
                    <Route
                        exact
                        path="/all-users"
                        render={() => <AllUsers />}
                    />
                </Switch>
            </BrowserRouter>
        )
    );
}

export default App;
