import movie_image from "./movie_image.jpg";

import "./RentalCompact.css";

function RentalCompact(props) {
    return (
        <div className="rental-compact-main">
            <img className="img" src={movie_image} alt="" />

            <h3>{props.vhs.title}</h3>
            <p>{props.vhs.year}</p>
            {props.dueDate && (
                <p>Due: {props.dueDate.split(" ")[0].replaceAll("-", ". ")}</p>
            )}
        </div>
    );
}

export default RentalCompact;
