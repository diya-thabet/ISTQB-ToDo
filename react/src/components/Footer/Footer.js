import React from "react";
import FooterCSS from "./Footer.module.css";
import EmailIcon from "@mui/icons-material/Email";
import GitHubIcon from "@mui/icons-material/GitHub";
import LinkedInIcon from "@mui/icons-material/LinkedIn";

const Footer = () => {
  return (
    <div className={FooterCSS.Footer}>
      <div className={FooterCSS.footercontainer}>
        <h1>Dhia Thabet | 2025</h1>
        <div className={FooterCSS.icons}>
          <a
            href="https://www.linkedin.com/in/dhiathabet"
            target="_blank"
            rel="noreferrer"
          >
            <LinkedInIcon className={FooterCSS.icon} />
          </a>
          <a
            href="https://github.com/diya-thabet"
            target="_blank"
            rel="noreferrer"
          >
            <GitHubIcon className={FooterCSS.icon} />
          </a>
          <a href="mailto:mohamaddhia@gmail.com">
            <EmailIcon className={FooterCSS.icon} />
          </a>
        </div>
      </div>
    </div>
  );
};

export default Footer;
