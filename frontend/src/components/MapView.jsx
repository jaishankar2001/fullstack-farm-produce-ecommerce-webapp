import React, { useMemo, useEffect, useState } from "react";
import { GoogleMap, MarkerF, useJsApiLoader } from "@react-google-maps/api";
import AutoComplete from "react-google-autocomplete";

const MapView = ({ setSelectedLocation, selectedLocation }) => {
  const [gmapsLoaded, setGmapsLoaded] = useState(false);

  useEffect(() => {
    window.initMap = () => setGmapsLoaded(true);
    const gmapScriptEl = document.createElement(`script`);
    gmapScriptEl.src = `https://maps.googleapis.com/maps/api/js?key=AIzaSyC92l4kh5h0HvZxwjtRg_F_uIwDCphriQI&libraries=places&callback=initMap`;
    document
      .querySelector(`body`)
      .insertAdjacentElement(`beforeend`, gmapScriptEl);
  }, []);

  const center = useMemo(() => ({ lat: 44.6475811, lng: -63.5727683 }), []);

  const handlePlaceSelect = async (lat, lng) => {
    const latLng = {
      lat,
      lng,
    };
    console.log("lat, lng", lat, lng, latLng);
    setSelectedLocation(latLng);
  };
  const [map, setMap] = React.useState(null);

  const onLoad = React.useCallback(function callback(map) {
    const bounds = new window.google.maps.LatLngBounds(center);
    map.setZoom(10);
    setMap(map);
  }, []);

  const onUnmount = React.useCallback(function callback(map) {
    setMap(null);
  }, []);

  return (
    <div>
      {!gmapsLoaded ? (
        <h1>Loading...</h1>
      ) : (
        <>
          <AutoComplete
            apiKey={"AIzaSyC92l4kh5h0HvZxwjtRg_F_uIwDCphriQI"}
            onPlaceSelected={(place) =>
              handlePlaceSelect(
                place.geometry.location.lat(),
                place.geometry.location.lng()
              )
            }
          />
          <GoogleMap
            mapContainerStyle={{
              height: "400px",
            }}
            center={selectedLocation}
            zoom={10}
            onLoad={onLoad}
            onUnmount={onUnmount}
          >
            <MarkerF
              position={selectedLocation}
              draggable={true}
              onDragEnd={(e) => {
                handlePlaceSelect(e.latLng.lat(), e.latLng.lng());
              }}
            />
          </GoogleMap>
          <h1>
            {selectedLocation.lat} {selectedLocation.lng}
          </h1>
        </>
      )}
    </div>
  );
};

export default MapView;
