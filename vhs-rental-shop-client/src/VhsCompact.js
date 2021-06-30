import { useState } from "react";
import { Redirect } from "react-router-dom";

import movie_image from "./movie_image.jpg";

import "./VhsCompact.css";

function VhsCompact(props) {
    const [openVhs, setOpenVhs] = useState(false);

    return openVhs ? (
        <Redirect to={"/vhses/" + props.id} />
    ) : (
        <div className="vhs-compact-main" onClick={() => setOpenVhs(true)}>
            <img className="img" src={movie_image} alt="" />

            <h3>{props.title}</h3>
            <p>{props.year}</p>
        </div>
    );
}

export default VhsCompact;
