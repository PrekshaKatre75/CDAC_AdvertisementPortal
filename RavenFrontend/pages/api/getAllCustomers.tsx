import { baseURL } from "@/constants";

export const GetAllCustomers = async () => {
  try {
    const res = await fetch(`${baseURL}/allCustomers`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });

    // Parse the response data as JSON
    const data = await res.json();

    // Log the parsed data
    // console.log("response data", data);

    // Return the parsed data
    return data;
  } catch (error) {
    console.error("Error:", error);
  }
};
