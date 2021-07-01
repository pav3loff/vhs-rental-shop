import axios from "axios";

import { STATUS_OK, STATUS_CREATED } from "./constants";

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

export async function logout() {
    await axios({
        url: "/logout",
        method: "POST",
    })
        .then(() => {})
        .catch(() => {});
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
                throw new Error();
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
                throw new Error();
            }
        })
        .catch(() => {});

    return vhs;
}

export async function getUser(username) {
    let user = { username: "", firstName: "", lastName: "", rentals: [] };

    await axios({
        url: "/api/users/" + username,
        method: "GET",
    })
        .then((response) => {
            if (response.status === STATUS_OK) {
                user = response.data;
            } else {
                throw new Error("Unauthorized!");
            }
        })
        .catch(() => {});

    return user;
}

export async function getRental(id) {
    let rental = { vhs: {}, dateRented: "", duedate: "" };

    await axios({
        url: "/api/rentals/" + id,
        method: "GET",
    })
        .then((response) => {
            if (response.status === STATUS_OK) {
                rental = response.data;
            } else {
                throw new Error("Unauthorized!");
            }
        })
        .catch(() => {});

    return rental;
}

export async function getUserInfo() {
    let responseData = {
        isSuccessful: false,
        userInfo: { username: "", isAdmin: false },
    };

    await axios({
        url: "/user-info",
        method: "GET",
    })
        .then((response) => {
            if (response.status === STATUS_OK) {
                responseData.isSuccessful = true;
                responseData.userInfo.username = response.data.username;
                responseData.userInfo.isAdmin =
                    response.data.authorities[0].authority === "ROLE_ADMIN"
                        ? true
                        : false;
            } else {
                throw new Error("Unauthorized!");
            }
        })
        .catch(() => {});

    return responseData;
}

export async function isVhsAvailable(id) {
    let isAvailable = true;

    await axios({
        url: "/api/vhses/" + id + "/available",
        method: "GET",
    })
        .then((response) => {
            if (response.status === STATUS_OK) {
                isAvailable = response.data;
            } else {
                throw new Error("Unauthorized!");
            }
        })
        .catch(() => {});

    return isAvailable;
}

export async function getRentalByVhsId(vhsId) {
    let responseData = {
        isSuccessful: false,
        rental: { dateRented: "", dueDate: "", lateFee: "" },
    };

    await axios({
        url: "/api/rentals/vhs-id=" + vhsId,
        method: "GET",
    })
        .then((response) => {
            if (response.status === STATUS_OK) {
                responseData.isSuccessful = true;
                responseData.dateRented = response.data.dateRented;
                responseData.dueDate = response.data.dueDate;
                responseData.lateFee = response.data.lateFee;
            } else {
                throw new Error("Unauthorized!");
            }
        })
        .catch(() => {});

    return responseData;
}

export async function getAllUsers() {
    let users = [];

    await axios({
        url: "/api/users",
        method: "GET",
    })
        .then((response) => {
            if (response.status === STATUS_OK) {
                users = response.data;
            } else {
                throw new Error("Unauthorized!");
            }
        })
        .catch(() => {});

    return users;
}

export async function returnRental(id) {
    let responseData = {
        isSuccessful: false,
        info: {},
    };

    await axios({
        url: "/api/rentals/" + id,
        method: "PUT",
    })
        .then((response) => {
            if (response.status === STATUS_OK) {
                responseData.isSuccessful = true;
                responseData.info = response.data;
            } else {
                throw new Error("Unauthorized!");
            }
        })
        .catch(() => {});

    return responseData;
}

export async function deleteRental(id) {
    await axios({
        url: "/api/rentals/" + id,
        method: "DELETE",
    })
        .then(() => {})
        .catch(() => {});
}

export async function addRental(vhsId, username) {
    var success = false;

    await axios({
        url: "/api/rentals",
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        data: { username: username, vhsId: vhsId },
    })
        .then((response) => {
            if (response.status === STATUS_CREATED) {
                success = true;
            } else {
                throw new Error("Incorrect credentials!");
            }
        })
        .catch(() => {});

    return success;
}
