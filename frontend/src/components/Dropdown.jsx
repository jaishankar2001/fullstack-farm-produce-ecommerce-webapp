import React, { useState } from "react";

function Dropdown({ options, onSelect, selectedValue }) {
  const [selectedOption, setSelectedOption] = useState(selectedValue);

  const handleOptionChange = (event) => {
    const selectedValue = event.target.value;
    setSelectedOption(selectedValue);
    onSelect(selectedValue);
  };
  return (
    <div>
      <select
        id="dropdown"
        value={selectedOption}
        onChange={handleOptionChange}
        class="form-control dropdown-toggle"
      >
        {options.map((option) => (
          <option key={option.id} value={option.name}>
            {option.name}
          </option>
        ))}
      </select>
    </div>
  );
}

export default Dropdown;
