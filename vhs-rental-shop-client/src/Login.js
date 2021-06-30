import { useState } from "react";
import { Redirect } from "react-router-dom";

import { Button } from "@material-ui/core";

import { login } from "./api/apiCalls";

import "./Login.css";

function Login(props) {
    const [formData, setFormData] = useState({ username: "", password: "" });

    function handleChange(event) {
        setFormData({ ...formData, [event.target.id]: event.target.value });
    }

    function handleSubmit(event) {
        event.preventDefault();

        login(formData).then((success) => {
            if (success) {
                localStorage.setItem("loggedUserUsername", formData.username);
                props.setLoggedIn(true);
            }
        });
    }

    return props.isLoggedIn ? (
        <Redirect to="/" />
    ) : (
        <div className="login-main">
            <form className="form" onSubmit={handleSubmit}>
                <label className="label" for="username">
                    Username
                </label>
                <input
                    className="form-input"
                    id="username"
                    value={formData.username}
                    onChange={handleChange}
                />

                <label className="label" for="Password">
                    Password
                </label>
                <input
                    className="form-input"
                    id="password"
                    value={formData.password}
                    type="password"
                    onChange={handleChange}
                />
                <Button className="btn-submit" type="submit">
                    Login
                </Button>
            </form>
        </div>
    );
}

export default Login;
