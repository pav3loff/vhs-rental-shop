import { useEffect, useState } from "react";

import { Link } from "react-router-dom";

import { getAllVhses } from "./api/apiCalls";
import VhsCompact from "./VhsCompact";

import "./Home.css";

function Home(props) {
    const [vhses, setVhses] = useState([]);

    useEffect(() => {
        getAllVhses().then((data) => setVhses(data));
    }, []);

    return (
        <div className="main">
            {vhses.map((vhs) => (
                <Link className="link" to={"/vhses/" + vhs.id} key={vhs.id}>
                    <VhsCompact {...vhs} />
                </Link>
            ))}
        </div>
    );
}

export default Home;
