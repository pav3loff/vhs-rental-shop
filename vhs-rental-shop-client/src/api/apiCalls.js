import axios from "axios";

import { STATUS_OK } from "./constants";

export async function login(formData) {
    var success = false;

    var bodyFormData = new FormData();

    bodyFormData.append("username", formData.username);
    bodyFormData.append("password", formData.password);

    await axios({
        url: "/login",
        method: "POST",
        headers: {
            "Content-Type": "multipart/form-data",
        },
        data: bodyFormData,
    })
        .then((response) => {
            if (response.status === STATUS_OK) {
                success = true;
            } else {
                throw new Error("Incorrect credentials!");
            }
        })
        .catch(() => {});

    return success;
}

export async function getAllVhses() {
    let vhses = [];

    await axios({
        url: "/api/vhses",
        method: "GET",
    })
        .then((response) => {
            if (response.status === STATUS_OK) {
                vhses = response.data;
            } else {
                throw new Error("Incorrect credentials!");
            }
        })
        .catch(() => {});

    return vhses;
}

export async function getVhs(id) {
    let vhs = { title: "", year: "", plot: "" };

    await axios({
        url: "/api/vhses/" + id,
        method: "GET",
    })
        .then((response) => {
            if (response.status === STATUS_OK) {
                vhs = response.data;
            } else {
                throw new Error("Incorrect credentials!");
            }
        })
        .catch(() => {});

    return vhs;
}
