import React, { useEffect, useRef } from 'react';

const Map = ({ farmLoc, selectedLocation, setSelectedLocation }) => {
  const mapRef = useRef(null);
  const google_api_key = 'AIzaSyC92l4kh5h0HvZxwjtRg_F_uIwDCphriQI';

  useEffect(() => {
    const loadGoogleMapsScript = () => {
      const script = document.createElement('script');
      script.src = `https://maps.googleapis.com/maps/api/js?key=${google_api_key}&libraries=places&callback=initMap`;
      script.defer = true;
      script.async = true;

      script.onload = () => {
        initializeMap();
      };

      document.head.appendChild(script);
    };

    const initializeMap = () => {
      const mapOptions = {
        center: selectedLocation,
        zoom: 13,
      };

      const map = new window.google.maps.Map(mapRef.current, mapOptions);
      var mIcon = {
        path: window.google.maps.SymbolPath.Marker,
        fillOpacity: 1,
        fillColor: '#pale',
        strokeOpacity: 1, 
        strokeWeight: 1,
        strokeColor: '#333',
        scale: 14
      };
      farmLoc.forEach(({ id, lat, long }) => {
        var infowindow = new window.google.maps.InfoWindow({
          content: `${id}`
        });
        const marker = new window.google.maps.Marker({
          position: { lat, lng: long },
          map,
          icon: mIcon,
          title: `${id}`,
          label: {color: '#000', fontSize: '12px', fontWeight: '600',
          text: `${id}`}
        });
        marker.addListener('mouseover', () => {
          infowindow.open(map,marker);
        });
        marker.addListener('mouseout', function() {
          infowindow.close();
      });
      });
    };

    if (window.google) {
      initializeMap();
    } else {
      loadGoogleMapsScript();
    }
  }, [farmLoc, selectedLocation]);

  return (
    <div
      ref={mapRef}
      style={{ width: '100%', height: '800px', border: '1px solid #ccc' }}
    />
  );
};

export default Map;