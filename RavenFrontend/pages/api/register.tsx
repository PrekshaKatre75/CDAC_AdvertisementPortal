import { baseURL } from "@/constants";

export const RegisterUser = async (props:any) => {
    const data = {
      email: props.email,
      password: props.password,
      mobileNo: props.contact,
      address: props.address,
      lname: props.lname,
      fname: props.fname,
    };
  
    try {
      const res = await fetch(`${baseURL}/registerCustomer`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });
  
      // Log the response to see the details in the console
      console.log("response", res);
  
      // Return the response directly
      return res;
    } catch (error) {
      console.error("Error:", error);
    }
  };
  