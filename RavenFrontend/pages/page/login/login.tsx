import Link from "next/link";
import React, { useEffect, useState } from "react";
import Image from "next/image";
import Layout from "@/pages/Components/bLayout";
import BLayout from "@/pages/Components/bLayout";
import { Box, Modal } from "@mui/material";
import Typography from "@mui/material/Typography";
import { RegisterUser } from "@/pages/api/register";
import { GetAllCustomers } from "@/pages/api/getAllCustomers";

const Login = () => {
  const [rememberMe, setRememberMe] = useState(false);
  const [open, setOpen] = React.useState(false);
  const [userData, setUserData] = useState<any>([]);
  const handleClose = () => setOpen(false);
  const [packageName, setPackageName] = useState();
  const handleOpen = () => setOpen(true);
  const [isFormValid, setIsFormValid] = useState(false);
  const [isValidate, setIsValidate] = useState(false);
  const [currentUser, setCurrentUser] = useState<any>([]);
  let fetchedData: any[];
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    contact: "",
    address: "",
    fname: "",
    lname: "",
  });
  const [loginData, setLoginData] = useState({
    email: "",
    password: "",
  });

  // console.log("user is valid", isValidate);

  const handleSubmit = async () => {
    // console.log("handleSubmit called");
    if (!isValidate) {
      alert("User does not exist, please register");
    }
  sessionStorage.setItem("userRole",currentUser.role);
    // setCurrentUser(foundUser);
  };

  useEffect(() => {
    if (loginData.email && loginData.password) {
      const foundUser = userData.find(
        (user: any) => user.email === loginData.email
      );
      setCurrentUser(foundUser);
      const isEmailValid = foundUser !== undefined;
      const isPasswordValid = foundUser?.password === loginData.password;
      setIsValidate(isEmailValid && isPasswordValid);
      // sessionStorage.setItem("userRole", userData.role);
    }
  }, [loginData.email && loginData.password]);
  
  useEffect(() => {
    const getData = async () => {
      fetchedData = await GetAllCustomers();
      console.log("response data", fetchedData);
      setUserData(fetchedData);
    };
    getData();
  }, []);

  const handleRegisterSubmit = async (e: any) => {
    e.preventDefault();
    console.log("handle register submit called", e);
    try {
      const fetchedData = await RegisterUser(formData);
      // console.log("api hit response", fetchedData);
    } catch (error) {
      //console.error(error);
    }
    // console.log("Form submitted");
    // console.log("form data",formData);
  };

  const handleLoginDataChange = (e: any) => {
    const { name, value } = e.target;
    setLoginData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
    if (loginData.email && loginData.password) {
      setIsFormValid(true);
    }
  };

  const handleInputChange = (e: any) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };
  const style = {
    position: "absolute" as "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 500,
    maxHeight: "80vh", // Maximum height as 80% of viewport height
    overflowY: "auto", // Enable vertical scroll if content overflows
    bgcolor: "background.paper",
    border: "2px solid #D69E2E", // Yellow-600 color
    borderRadius: "0.375rem", // Adding rounded corners
    boxShadow: 24,
    p: 4,
  };

  return (
    <div className="flex flex-col items-center justify-center px-6 mx-auto my-16 lg:py-0">
      <a
        href="#"
        className="flex items-center mb-6 text-2xl font-semibold text-gray-900 dark:text-white"
      >
        {/* image */}
      </a>
      <div className="w-full bg-white rounded-lg shadow dark:border border-2 md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
        <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
          <h1 className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
            Sign in to your account
          </h1>
          <form className="space-y-4 md:space-y-6">
            <div>
              <label
                htmlFor="email"
                className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >
                Your email
              </label>
              <input
                type="email"
                name="email"
                id="email"
                className="w-full h-10 border border-gray-800 rounded-md"
                placeholder="name@company.com"
                value={loginData.email}
                onChange={handleLoginDataChange}
                required
              />
            </div>
            <div>
              <label
                htmlFor="password"
                className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >
                Password
              </label>
              <input
                type="password"
                name="password"
                id="password"
                placeholder="••••••••"
                className="w-full h-10 border border-gray-800 rounded-md"
                value={loginData.password}
                onChange={handleLoginDataChange}
                minLength={8} // Minimum password length
                required
              />
            </div>
            <Link href={isValidate ? "/page/Dashboard/Dashboard" : ""}>
              <button
                type="submit"
                className="w-full text-white bg-yellow-700 hover:bg-primary-700 focus:ring-4 focus:outline-none
     focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 mt-8 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800"
                disabled={!isFormValid}
                onClick={handleSubmit}
              >
                Sign in
              </button>
            </Link>

            <p className="text-sm font-light text-gray-500 dark:text-gray-400">
              Don’t have an account yet?{" "}
              <a
                className="font-medium text-primary-600 hover:underline dark:text-primary-500"
                onClick={handleOpen}
              >
                Sign up
              </a>
            </p>
            <Modal
              open={open}
              onClose={handleClose}
              aria-labelledby="modal-modal-title"
              aria-describedby="modal-modal-description"
            >
              <Box sx={style}>
                <body
                  className="bg-gray-100 flex items-center justify-center
                "
                >
                  <div className="bg-white rounded shadow-md w-full">
                    <h2 className="text-2xl font-semibold mb-4 pb-2 text-yellow-700 border-b-2 border-yellow-800">
                      Registration
                    </h2>
                    <form
                      onSubmit={(event: any) => handleRegisterSubmit(event)}
                    >
                      <div className="mb-4">
                        <label
                          htmlFor="fname"
                          className="block text-sm font-medium text-gray-600 mb-1"
                        >
                          First Name
                        </label>
                        <input
                          type="text"
                          id="fname"
                          name="fname"
                          className="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-300"
                          value={formData.fname}
                          onChange={handleInputChange}
                          required
                        />
                      </div>
                      <div className="mb-4">
                        <label
                          htmlFor="lname"
                          className="block text-sm font-medium text-gray-600 mb-1"
                        >
                          Last Name
                        </label>
                        <input
                          type="text"
                          id="lname"
                          name="lname"
                          className="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-300"
                          value={formData.lname}
                          onChange={handleInputChange}
                          required
                        />
                      </div>
                      <div className="mb-4">
                        <label
                          htmlFor="email"
                          className="block text-sm font-medium text-gray-600 mb-1"
                        >
                          Email
                        </label>
                        <input
                          type="email"
                          id="email"
                          name="email"
                          className="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-300"
                          value={formData.email}
                          onChange={handleInputChange}
                          required
                        />
                      </div>
                      <div className="mb-4">
                        <label
                          htmlFor="password"
                          className="block text-sm font-medium text-gray-600 mb-1"
                        >
                          Password
                        </label>
                        <input
                          type="password"
                          id="password"
                          name="password"
                          className="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-300"
                          value={formData.password}
                          onChange={handleInputChange}
                          required
                        />
                      </div>
                      <div className="mb-4">
                        <label
                          htmlFor="contact"
                          className="block text-sm font-medium text-gray-600 mb-1"
                        >
                          Contact
                        </label>
                        <input
                          type="text"
                          id="contact"
                          name="contact"
                          className="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-300"
                          value={formData.contact}
                          onChange={handleInputChange}
                          required
                        />
                      </div>
                      <div className="mb-4">
                        <label
                          htmlFor="address"
                          className="block text-sm font-medium text-gray-600 mb-1"
                        >
                          Address
                        </label>
                        <textarea
                          id="address"
                          name="address"
                          className="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-300"
                          value={formData.address}
                          onChange={handleInputChange}
                          rows={3}
                          required
                        ></textarea>
                      </div>
                      <button
                        type="submit"
                        className="w-full bg-yellow-700 text-white py-2 rounded-lg hover:bg-yellow-800 transition duration-300"
                        onClick={handleClose}
                      >
                        Register
                      </button>
                    </form>
                    <p className="text-gray-600 text-sm mt-4">
                      Already a user?{" "}
                      <a href="/" className="text-yellow-700">
                        Login
                      </a>
                    </p>
                  </div>
                </body>
              </Box>
            </Modal>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;
