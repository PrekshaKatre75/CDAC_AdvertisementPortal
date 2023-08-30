import React, { createContext, useState } from "react";

interface AppContextProps {
  state: number;
  toggleState: (newValue: number) => void;
}

// Create the context
const AppContext = createContext<AppContextProps>({
  state: 0,
  toggleState: () => {},
});

// Create the provider component
export const AppContextProvider: React.FC<any> = ({ children }) => {
  const [state, setState] = useState<number>(0);

  const toggleState = (newValue: number) => {
    setState(newValue);
  };

  return (
    <AppContext.Provider
      value={{
        state,
        toggleState,
      }}
    >
      {children}
    </AppContext.Provider>
  );
};

// Export the context
export const useAppContext = () => React.useContext(AppContext);
