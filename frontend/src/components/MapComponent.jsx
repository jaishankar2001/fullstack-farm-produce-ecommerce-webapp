import React, { useEffect, useState } from "react";
import MapView from "./MapView";

function MapComponent(props) {
  const [selectedLocation, setSelectedLocation] = useState({
    lat: 44.6475811,
    lng: -63.5727683,
  });

  useEffect(() => {
    console.log("" + selectedLocation);
  }, [selectedLocation]);
  return (
    <div>
      <h2>Pinpoint Your Farm Location</h2>
      <MapView
        setSelectedLocation={setSelectedLocation}
        selectedLocation={selectedLocation}
      />
    </div>
  );
}

export default MapComponent;
