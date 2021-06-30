import { useEffect, useState } from "react";

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
                <VhsCompact key={vhs.id} {...vhs} />
            ))}
        </div>
    );
}

export default Home;
