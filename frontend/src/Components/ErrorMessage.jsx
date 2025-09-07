import { RiCloseCircleLine } from "react-icons/ri";


// eslint-disable-next-line react/prop-types
function ErrorMessage({ setIsError, message }) {

   function OnClick() {
      setIsError(false);
   }

   return (
      <div className="rounded-md p-2 sm:p-3 border-red-700 border-2 bg-red-50 w-[30%] h-[40%] flex flex-col gap-7
       items-center justify-around">
         <RiCloseCircleLine className="text-6xl text-red-400" />
         <h1 className="text-md font-semibold text-red-900 text-center">
            {message || "Error"}
         </h1>
         <button className="py-1 px-2 sm:py-2 sm:px-3 border-2 border-red-500 bg-red-100 rounded-md"
          onClick={() => OnClick()}>
            OK
         </button>
      </div>
   );
}

export default ErrorMessage;
