import { useEffect, useState } from "react";

import { Link } from "react-router-dom";

import { getAllVhses } from "./api/apiCalls";
import VhsCompact from "./VhsCompact";

import "./Home.css";

function Home() {
    const [vhses, setVhses] = useState([]);

    useEffect(() => {
        getAllVhses().then((data) => setVhses(data));
    }, []);

    return (
        <div className="home-main">
            <h1 className="home-header-text">Our collection</h1>
            <div className="home-container">
                {vhses.map((vhs) => (
                    <Link
                        className="home-link"
                        to={"/vhses/" + vhs.id}
                        key={vhs.id}
                    >
                        <VhsCompact {...vhs} />
                    </Link>
                ))}
            </div>
        </div>
    );
}

export default Home;
