import user_image from "./user_image.png";

import "./User.css";

function User(props) {
    return (
        <div className="user-main">
            <img className="user-img" src={user_image} alt="" />
            <h2>{props.username}</h2>
            <h4>
                {props.firstName} {props.lastName}
            </h4>
        </div>
    );
}

export default User;
