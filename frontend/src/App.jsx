import { useState } from "react";
import ErrorMessage from "./Components/ErrorMessage";
import InputFile from "./Components/InputFile";
import OutputFile from "./Components/OutputFile";

const App = function () {

  const [isError, setIsError] = useState(false);
  const [message, setMessage] = useState("");

  const [output, setOutput] = useState(null);

  return (
    <div className="px-5 py-5 sm:px-10 sm:py-10">
      {
        isError && (
          <div className="fixed m-auto left-0 right-0 top-0 bottom-0 flex overflow-y-auto z-50 items-center justify-center backdrop-blur-md">
            <ErrorMessage setIsError={setIsError} message={message} />
          </div>
        )
      }
      <div className="flex flex-col sm:grid sm:grid-cols-[30%_auto] gap-4">
        <InputFile setIsError={setIsError} setMessage={setMessage} setOutput={setOutput} />
        {
          output !== null && <OutputFile output={output} />
        }
      </div>
    </div>
  );
}

export default App;
