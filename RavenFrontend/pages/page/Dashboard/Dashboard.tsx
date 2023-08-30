import { Card } from "@mui/material";
import React, { useEffect, useState } from "react";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { Box, Modal } from "@mui/material";
import { GetAllPackages } from "@/pages/api/getAllPackage";
import { GetAllCustomers } from "@/pages/api/getAllCustomers";
import { useAppContext } from "@/pages/Components/AppContext";
const Dashboard = () => {
  const [userData, setUserData] = useState<any>([]);

  // console.log(userRole)
  let getUserData: any[];
  useEffect(() => {
    const getData = async () => {
      getUserData = await GetAllCustomers();
      // //console.log("response data", fetchedData);
      setUserData(getUserData);
    };
    getData();
  }, []);

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

  const [open, setOpen] = React.useState(false);
  const { state, toggleState } = useAppContext();
  const toggle = (id: any) => {
    toggleState(id);
  };
  const [openPurchaseModal, setOpenPurchaseModal] = useState(false);
  const [packageData, setPackageData] = useState<any>([]);
  // const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const handlePurchaseClose = () => setOpenPurchaseModal(false);
  const [packageName, setPackageName] = useState<any>();
  const handleOpenDialog = (name: any) => {
    setPackageName(name);
    setOpen(true);
  };
  const handlePurchaseDialog = (name: any) => {
    setPackageName(name);
    setOpenPurchaseModal(true);
  };
  let fetchedData: any[];
  useEffect(() => {
    const getData = async () => {
      fetchedData = await GetAllPackages();
      // //console.log("response data", fetchedData);
      setPackageData(fetchedData);
      const userRole = sessionStorage.getItem("userRole");
    };
    getData();
  }, []);
// console.log("state",state)
  const handlePurchase = (id: any) => {
    toggle(id);
    alert("Purchased successfully!");
    setOpenPurchaseModal(false);
    // console.log("payment gateway", e);
  };

  // console.log("packageData", packageData);
  type PackageName = "Bronze" | "Silver" | "Gold" | "Platinum";

  const getStylesForPackage = (packageName: PackageName) => {
    return packageStyles[packageName] || packageStyles["Bronze"];
  };

  const packageStyles = {
    Bronze: {
      borderColor: "amber-800",
      textColor: "amber-800",
      buttonColor: "amber-800",
      buttonHoverColor: "amber-100",
    },
    Silver: {
      borderColor: "gray-400",
      textColor: "gray-400",
      buttonColor: "gray-400",
      buttonHoverColor: "gray-100",
    },
    Gold: {
      borderColor: "yellow-600",
      textColor: "yellow-600",
      buttonColor: "yellow-600",
      buttonHoverColor: "yellow-100",
    },
    Platinum: {
      borderColor: "gray-500",
      textColor: "gray-500",
      buttonColor: "gray-500",
      buttonHoverColor: "gray-200",
    },
  };

  return (
    <>
      <div className="text-xl text-yellow-700 font-semibold border-b-2 border-yellow-800 pb-2">
        Dashboard
      </div>
      <div className="p-7"></div>
      <div className="flex space-x-14 pl-9">
        {packageData &&
          packageData.map((packageItem: any) => {
            const styles = getStylesForPackage(packageItem.pname);

            return (
              <Card
                key={packageItem.id}
                className={`w-1/5 shadow-xl rounded-md border-${styles.borderColor} border-t-2 border overflow-hidden bg-white`}
              >
                <CardMedia
                  className="h-40 w-full object-cover"
                  image={`/${packageItem.pname.toLowerCase()}.jpg`}
                  title={`${packageItem.pname} image`}
                />
                <CardContent className="p-4">
                  <div
                    className={`text-xl border-b-2 border-${styles.borderColor} text-${styles.textColor}`}
                  >
                    {packageItem.pname}
                  </div>
                </CardContent>
                <CardActions
                  className={`border-t border-${styles.borderColor} py-2`}
                >
                  <Button
                    size="small"
                    className={`text-${styles.buttonColor} font-medium hover:bg-${styles.buttonHoverColor}`}
                    onClick={() => handleOpenDialog(packageItem.pname)}
                  >
                    View Details
                  </Button>
                  <Button
                    size="small"
                    className={`text-${styles.buttonColor} font-semibold hover:bg-${styles.buttonHoverColor}`}
                    onClick={() => handlePurchaseDialog(packageItem.pname)}
                  >
                    Buy Now
                  </Button>
                </CardActions>
              </Card>
            );
          })}
        <Modal
          open={open}
          onClose={handleClose}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
        >
          <Box sx={style}>
            <div
              className={`border-b-2 text-2xl font-semibold border-${
                packageName && packageName.toLowerCase()
              }-800`}
            >
              {packageName}
            </div>
            <div className="text-lg font-semibold pt-5">
              Price:{" "}
              {
                packageData.find(
                  (packageItem: any) => packageItem.pname === packageName
                )?.pcost
              }{" "}
              INR
            </div>
            <div className="text-lg font-semibold pt-2">
              Validity:{" "}
              {
                packageData.find(
                  (packageItem: any) => packageItem.pname === packageName
                )?.pvalidity
              }{" "}
              Months
            </div>
          </Box>
        </Modal>
        <Modal
          open={openPurchaseModal}
          onClose={handlePurchaseClose}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
        >
          <Box sx={style}>
            <div
              className={`border-b-2 border-gray-600 text-2xl font-semibold border-${
                packageName && packageName.toLowerCase()
              }-800`}
            >
              {packageName}
            </div>
            <div className="text-lg font-semibold mt-5 border-2 p-2 border-gray-600 rounded-md">
              Price:{" "}
              {
                packageData.find(
                  (packageItem: any) => packageItem.pname === packageName
                )?.pcost
              }{" "}
              INR
            </div>
            <div className="text-lg font-semibold mt-5 border-2 p-2 border-gray-600 rounded-md">
              Validity:{" "}
              {
                packageData.find(
                  (packageItem: any) => packageItem.pname === packageName
                )?.pvalidity
              }{" "}
              Months
            </div>
            <button
              className="flex ml-28 btn mt-5 bg-yellow-700 text-white px-3 py-1 rounded-md"
              onClick={() => {
                const selectedPackage = packageData.find(
                  (packageItem: any) => packageItem.pname === packageName
                );
                if (selectedPackage) {
                  handlePurchase(selectedPackage.id);
                }
              }}
            >
              Purchase
            </button>
          </Box>
        </Modal>
      </div>
    </>
  );
};

export default Dashboard;
