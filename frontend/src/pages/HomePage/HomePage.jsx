import React from 'react';
import { styles } from './styles.js'


export const HomePage = () => {
  return (
    <div style={styles.backgroundStyle}>
      <h1 style={styles.headingStyle}>Ecopick</h1>
      <p style={styles.textStyle}>
        Farm to Table: Connecting Cultivators with Consumers for Fresh, Organic Bounty!
      </p>
    </div>
  );
};
