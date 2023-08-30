import React, { useEffect, useState } from "react";
import { useAppContext } from "@/pages/Components/AppContext";
import { GetAllPackages } from "@/pages/api/getAllPackage";
import { Card } from "@mui/material";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";

const CurrentPackage = () => {
  const { state, toggleState } = useAppContext();
  const [packageData, setPackageData] = useState<any[]>([]);
  const [selectedPackage, setSelectedPackage] = useState<any | null>(null);

  useEffect(() => {
    const getData = async () => {
      const fetchedData = await GetAllPackages();
      setPackageData(fetchedData);
    };
    getData();
  }, []);

  useEffect(() => {
    if (state !== null) {
      const selectedPackage = packageData.find((packageItem) => packageItem.id === state);
      setSelectedPackage(selectedPackage);
    }
  }, [state, packageData]);

  return (
    <div>
      <div className="text-xl text-yellow-700 font-semibold border-b-2 border-yellow-800 pb-2">
        Current Package
      </div>
      <div className="p-7"></div>
      <div className="flex space-x-14 pl-9">
      {selectedPackage && (
        <Card className="w-1/5 shadow-xl rounded-md border border-yellow-800 overflow-hidden bg-white">
          <CardMedia
            className="h-40 w-full object-cover"
            image={`/${selectedPackage.pname.toLowerCase()}.jpg`}
            title={`${selectedPackage.pname} image`}
          />
          <CardContent className="p-4">
            <div className={`text-xl border-b-2 border-yellow-800 text-yellow-700`}>
              {selectedPackage.pname}
            </div>
          </CardContent>
        </Card>
      )}
      </div>
    </div>
  );
};

export default CurrentPackage;
