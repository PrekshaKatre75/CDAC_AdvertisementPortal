import React, { ReactNode } from "react";
import Sidebar from "./Sidebar";
import Navbar from "./Navbar";

interface LayoutProps {
  children: ReactNode;
}
const Layout: React.FC<LayoutProps> = ({ children }: any) => {
  return (
    <div className="flex text-black h-screen bg-white dark:bg-gray-900">
      <Sidebar />
      <div className="flex-auto">
        <div>
        <Navbar />
        </div>
        {/* Page content */}
        {/* use AppContext for collapsible content */}
        <main className="p-6 w-4/5 ml-auto mt-20">{children}</main>
      </div>
    </div>
  );
};

export default Layout;