import React, { useEffect, useState } from "react";
import { BsArrowLeftCircle } from "react-icons/bs";
import DashboardIcon from "@mui/icons-material/Dashboard";
import LocalMallIcon from "@mui/icons-material/LocalMall";
import SettingsIcon from "@mui/icons-material/Settings";
import Link from "next/link";
import GroupIcon from '@mui/icons-material/Group';
// import HamburgerButton from './HamburgerMenuButton/HamburgerButton'

const Sidebar = () => {
  const [state, setState] = useState(true);
  const toggleState = () => {
    setState(!state);
    // console.log("------------", state);
  };
  
  useEffect(() => {
    // Retrieve userRole from sessionStorage
    const storedUserRole = sessionStorage.getItem("userRole") || "";
    // Set the userRole to the state
    setUserRole(storedUserRole);
  }, []);

  const [userRole, setUserRole] = useState("");

  const Footer = () => {
    return (
      <footer className="footer z-10 bg-white text-neutral-content border-t-2 border-yellow-800 bottom-0 w-full fixed">
        <div className="container mx-auto flex justify-between items-center h-16">
          <div
            className={`${state ? "mx-60" : ""} flex items-center text-black`}
          >
            <p className="mr-2 text-yellow-700">
              {" "}
              2023 - All Rights Reserved,{" "}
              <strong className="text-yellow-700">Advertisement</strong>
            </p>
          </div>
          <div className="flex gap-4 text-yellow-700">
            <div>About Us</div>
            <div>Help</div>
            <div>Contact Us</div>
          </div>
        </div>
      </footer>
    );
  };

  return (
    <>
      <div className="w-1/5 top-16 mt-2 bottom-0 z-20 shadow-2xl  hidden border-yellow-800 sm:block fixed  transition-all duration-300 bg-white border-r-2">
        <div
          className={`${
            !state && "rotate-180"
          }  absolute mr-1.5 text-xl bg-white fill-slate-800 rounded-full cursor-pointer top-9 -right-4 dark:fill-gray-400 dark:bg-gray-800`}
          onClick={toggleState}
        >
          {/* <BsArrowLeftCircle /> */}
        </div>

        <ul className="space-y-1.5 pt-5">
          <li>
            <Link
              href="/page/Dashboard/Dashboard"
              className="flex items-center gap-x-3.5 text-lg pb-2 px-2.5
             text-yellow-700 rounded-md hover:bg-yellow-50 
             "
            >
              <DashboardIcon />
              <span className={`${!state && "hidden"} origin-left hover:block`}>
                Dashboard
              </span>
            </Link>
          </li>
          <li className="hs-accordion" id="users-accordion">
            <Link
              href="/page/CurrentPackage/CurrentPackage"
              className="flex items-center gap-x-3.5 py-2 px-2.5 text-lg
              text-yellow-700 rounded-md hover:bg-yellow-50 
              "
            >
              <LocalMallIcon />
              <span className={`${!state && "hidden"} origin-left hover:block`}>
                Current Package
              </span>
            </Link>
          </li>
          {userRole == "admin" && (
            <li>
              <Link
                href="/page/Customers/Customers"
                className="flex items-center gap-x-3.5 py-2 px-2.5 text-lg
              text-yellow-700 rounded-md hover:bg-yellow-50 
              "
              >
                <GroupIcon />
                <span
                  className={`${!state && "hidden"} origin-left hover:block`}
                >
                  Customers
                </span>
              </Link>
            </li>
          )}
          <li>
            <Link
              href="/page/Settings/Settings"
              className="flex items-center gap-x-3.5 py-2 px-2.5 text-lg
              text-yellow-700 rounded-md hover:bg-yellow-50 
              "
            >
              <SettingsIcon />
              <span className={`${!state && "hidden"} origin-left hover:block`}>
                Settings
              </span>
            </Link>
          </li>
        </ul>
      </div>
      <Footer />
    </>
  );
};

export default Sidebar;
