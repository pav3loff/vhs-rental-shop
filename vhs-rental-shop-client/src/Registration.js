import { useState } from "react";
import { Redirect } from "react-router-dom";

import { Button } from "@material-ui/core";

import { registration } from "./api/apiCalls";

import "./Registration.css";

function Registration() {
    const [formData, setFormData] = useState({
        username: "",
        password: "",
        firstName: "",
        lastName: "",
    });
    const [isSuccessful, setSuccessful] = useState(false);

    function handleChange(event) {
        setFormData({ ...formData, [event.target.id]: event.target.value });
    }

    function handleSubmit(event) {
        event.preventDefault();

        registration(formData).then((success) => {
            if (success) {
                setSuccessful(true);
            }
        });
    }

    return (
        <>
            <div className="registration-main">
                <form className="registration-form" onSubmit={handleSubmit}>
                    <label className="registration-label">Username</label>
                    <input
                        className="registration-form-input"
                        id="username"
                        value={formData.username}
                        onChange={handleChange}
                    />

                    <label className="registration-label">Password</label>
                    <input
                        className="registration-form-input"
                        id="password"
                        value={formData.password}
                        type="password"
                        onChange={handleChange}
                    />

                    <label className="registration-label">First name</label>
                    <input
                        className="registration-form-input"
                        id="firstName"
                        value={formData.firstName}
                        onChange={handleChange}
                    />

                    <label className="registration-label">Last name</label>
                    <input
                        className="registration-form-input"
                        id="lastName"
                        value={formData.lastName}
                        onChange={handleChange}
                    />
                    <Button className="registration-btn-submit" type="submit">
                        Register
                    </Button>
                </form>
            </div>
            {isSuccessful && (
                <>
                    <Redirect to="/" />
                </>
            )}
        </>
    );
}

export default Registration;
