import { baseURL } from "@/constants";

export const purchasePackage = async (props: any) => {
  const data = {
    getpLogo: "ccc",
    pvalidity: 0,
    pcost: 0,
    pname: "string",
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
