import React, { useEffect, useRef } from "react";

const Map = ({ farmLoc, selectedLocation, setSelectedLocation }) => {
  const mapRef = useRef(null);
  const google_api_key = process.env.REACT_APP_MAP_KEY;
  const loadGoogleMapsScript = () => {
    const script = document.createElement("script");
    script.src = `https://maps.googleapis.com/maps/api/js?key=${google_api_key}&libraries=places`;
    script.defer = true;
    script.async = true;

    document.head.appendChild(script);
  };
  const initializeMap = () => {
    const mapOptions = {
      center: selectedLocation,
      zoom: 12,
    };

    const map = new window.google.maps.Map(mapRef.current, mapOptions);
    var mIcon = {
      path: window.google.maps.SymbolPath.Marker,
      fillOpacity: 1,
      fillColor: "#pale",
      strokeOpacity: 1,
      strokeWeight: 1,
      strokeColor: "#333",
      scale: 14,
    };
    farmLoc.forEach(({ name, lat, long }) => {
      var infowindow = new window.google.maps.InfoWindow({
        content: `${name}`,
      });
      const marker = new window.google.maps.Marker({
        position: { lat, lng: long },
        map,
        icon: mIcon,
        title: `${name}`,
        label: { color: "#000", fontSize: "12px", fontWeight: "600" },
      });
      marker.addListener("mouseover", () => {
        infowindow.open(map, marker);
      });
      marker.addListener("mouseout", function () {
        infowindow.close();
      });
    });
  };
  const delayedLoadGoogleMapsScript = () => {
    setTimeout(loadGoogleMapsScript, 500);
  };

  useEffect(() => {
    if (window.google) {
      initializeMap();
    } else {
      delayedLoadGoogleMapsScript();
    }
  }, [farmLoc, selectedLocation]);
  
  useEffect(() => {
    if (!window.google) {
      loadGoogleMapsScript();
    } else {
      initializeMap();
    }
  }, []);

  return (
    <div
      ref={mapRef}
      style={{ width: "100%", height: "800px", border: "1px solid #ccc" }}
    />
  );
};

export default Map;
