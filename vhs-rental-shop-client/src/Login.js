import { useState } from "react";
import { Redirect } from "react-router-dom";

import { Button } from "@material-ui/core";

import { getUserInfo, login } from "./api/apiCalls";

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
                getUserInfo().then((response) => {
                    props.setLoggedUserInfo(response);
                    props.setLoggedIn(true);
                });
            }
        });
    }

    return props.isLoggedIn ? (
        <Redirect to="/" />
    ) : (
        <div className="login-main">
            <form className="login-form" onSubmit={handleSubmit}>
                <label className="login-label">Username</label>
                <input
                    className="login-form-input"
                    id="username"
                    value={formData.username}
                    onChange={handleChange}
                />

                <label className="login-label">Password</label>
                <input
                    className="login-form-input"
                    id="password"
                    value={formData.password}
                    type="password"
                    onChange={handleChange}
                />
                <Button className="login-btn-submit" type="submit">
                    Login
                </Button>
            </form>
        </div>
    );
}

export default Login;
