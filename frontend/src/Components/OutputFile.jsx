/* eslint-disable react/prop-types */
import Table from "./Table";

const OutputFile = function({ output }) {
   const finishTime = [];
   const process = [];

   const arrival_time_array = output.arrival_time;
   const completion_time_array = output.completion_time;
   const length = output.length;

   finishTime.push(arrival_time_array[0]);
   finishTime.push(completion_time_array[0]);

   process.push(0);

   for(let i = 0; i < length - 1; i = i + 1) {
      if(completion_time_array[i] >= arrival_time_array[i + 1]) {
         finishTime.push(completion_time_array[i + 1]);
         process.push(i + 1);
      }
      else {
         process.push(-1);
         finishTime.push(arrival_time_array[i + 1]);
         process.push(i + 1);
         finishTime.push(completion_time_array[i + 1]);

      }
   }



   return (
      <div className="px-2 py-3 sm:px-4 sm:py-6 bg-slate-100 rounded-lg flex flex-col items-center gap-7 shadow-lg">
         {/* Grantt Chart and table will be shown here */}
         <div className="flex justify-between items-center items-between w-full">
            <h1 className="text-xl font-semibold tracking-wide">Output</h1>
            <span className="text-lg text-green-700 bg-green-200 rounded-md px-2 py-1 sm:px-3">{output.algorithm}</span>
         </div>
         <div className="flex flex-col items-center w-[85%]">
            <div className="flex flex-row flex-wrap gap-y-5 divide-x-2 divide-slate-50">
               {
                  process.map((item, count) => {
                     return (
                        <span className="flex flex-col" key={item}>
                           <div className={`w-[40px] h-[40px] flex justify-center items-center bg-blue-100 text-blue-400 border-blue-500 
                           ${count == 0 ? "border-l-[0.1rem]" : ""} border-t-[0.1rem] 
                           border-b-[0.1rem] border-r-[0.1rem] border-l-[0.1rem]`}>{item}</div>
                           {
                              count === finishTime.length - 2 ? (
                                 <span className="flex flex-row gap-x-4">
                                    <span>{finishTime[count]}</span>
                                    <span>{finishTime[finishTime.length - 1]}</span>
                                 </span>
                              ) : (
                                 <span>{finishTime[count]} </span>
                              )
                           }
                           
                        </span>
                     )
                  })
               }
            </div>
            {/* <div className="flex flex-row flex-wrap gap-x-8 divide-slate-50">
               {
                  finishTime.map((item, count) => {
                     return (
                        <div className="flex flex-row" key={item}>
                           <div></div>
                        </div>
                     )
                  })
               }
            </div> */}
         </div>
         <div className="bg-slate-200 px-2 py-1 sm:px-4 sm:py-2 w-full">
            <h1 className="text-center font-semibold">Abbreviation</h1>
            <h2>AT - Arrival Time</h2>
            <h2>BT - Burst Time</h2>
            <h2>CT - Completion Time</h2>
            <h2>TT - Turnaround Time</h2>
            <h2>WT - Waiting Time</h2>
         </div>
         <div className="w-full">
            <Table output={output} />
         </div>
      </div>
   );
}

export default OutputFile;
