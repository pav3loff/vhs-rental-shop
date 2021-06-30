import { useEffect, useState } from "react";

import { getVhs } from "./api/apiCalls";

import "./Vhs.css";

function Vhs(props) {
    const id = props.match.params.id;

    const [vhs, setVhs] = useState({ title: "", year: "", plot: "" });

    useEffect(() => {
        getVhs(id).then((data) => setVhs(data));
    }, [id]);

    return (
        <div className="vhs-main">
            <h1>{vhs.title}</h1>
            <h2>{vhs.year}</h2>
            <p>{vhs.plot}</p>
        </div>
    );
}

export default Vhs;
