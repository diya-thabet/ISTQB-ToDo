import { Email, GitHub, LinkedIn } from "@material-ui/icons";
import React from "react";
import FooterCSS from "./Footer.module.css";

const Footer = () => {
  return (
    <div className={FooterCSS.Footer}>
      <div className={FooterCSS.footercontainer}>
        <h1>Dhia Thabet | 2025</h1>
        <div className={FooterCSS.icons}>
          {" "}
          <a
            href="https://www.linkedin.com/in/dhiathabet"
            target="_blank"
            rel="noreferrer"
          >
            <LinkedIn className={FooterCSS.icon} />
          </a>
          <a href="https://github.com/diya-thabet" target="_blank" rel="noreferrer">
            <GitHub className={FooterCSS.icon} />
          </a>
          <a href="mailto:mohamaddhia@gmail.com">
            <Email className={FooterCSS.icon} />
          </a>
        </div>
      </div>
    </div>
  );
};

export default Footer;
