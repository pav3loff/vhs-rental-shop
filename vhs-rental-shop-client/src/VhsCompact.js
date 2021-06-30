import movie_image from "./movie_image.jpg";

import "./VhsCompact.css";

function VhsCompact(props) {
    return (
        <div className="vhs-compact-main">
            <img className="img" src={movie_image} alt="" />

            <h3>{props.title}</h3>
            <p>{props.year}</p>
        </div>
    );
}

export default VhsCompact;
