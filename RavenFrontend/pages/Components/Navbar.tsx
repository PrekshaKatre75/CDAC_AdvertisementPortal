import Link from "next/link";
import React, { useState } from "react";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import FitbitIcon from "@mui/icons-material/Fitbit";
import { Box, Modal } from "@mui/material";

const Navbar = () => {
  const [open, setOpen] = useState(false);
  const handleClose = () => setOpen(false);
  const style = {
    position: "absolute" as "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 400,
    bgcolor: "background.paper",
    border: "2px solid #D69E2E", // Yellow-600 color
    borderRadius: "0.375rem", // Adding rounded corners
    boxShadow: 24,
    p: 4,
  };
  const handleSignOut = () => setOpen(true);
  return (
    <nav className="bg-white shadow-xl z-10 border-yellow-800 border-b-2 fixed w-full">
      <div className="max-w-screen-xl flex flex-wrap items-center p-4 justify-between">
        <a href="/" className="flex items-center">
          <FitbitIcon className="h-10 w-10 text-yellow-800 mr-3 cursor-pointer" />
          <span className="self-center text-2xl font-semibold whitespace-nowrap text-yellow-800 ">
            Advertisement
          </span>
        </a>
        <div className="flex items-center">
          <button className="text-yellow-800  mr-4 border-2 border-yellow-800 p-1 px-2 rounded-md hover:bg-amber-100">
            Register Company
          </button>
          {/* User Icon */}
          <AccountCircleIcon className="h-9 w-9 text-yellow-800 mr-4 cursor-pointer" />

          {/* Sign Out Button */}
          <button
            className="text-yellow-800 -mr-28 border-2 border-yellow-800 rounded-md p-1 px-2 hover:bg-amber-100"
            onClick={handleSignOut}
          >
            Sign Out
          </button>
        </div>
      </div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <div className="flex justify-between items-center bg-gray-100 p-4 rounded-md shadow-md">
            <div className="text-lg font-semibold text-gray-800">
              Do you want to sign out?
            </div>
            <Link href="/">
              <button className="bg-yellow-600 hover:bg-yellow-700 text-white rounded-md py-1 px-3 focus:outline-none focus:ring focus:ring-yellow-300">
                Confirm
              </button>
            </Link>
          </div>
        </Box>
      </Modal>
    </nav>
  );
};

export default Navbar;
