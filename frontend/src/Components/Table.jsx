/* eslint-disable react/prop-types */

const Table = function ({ output }) {

   const data = [];

   const arrival_time_array = output.arrival_time;
   const burst_time_array = output.burst_time;
   const completion_time_array = output.completion_time;
   const turnaround_time_array = output.turnaround_time;
   const waiting_time_array = output.waiting_time;

   for (let i = 0; i < output.length; i = i + 1) {
      const array = [];
      array.push(i);
      array.push(arrival_time_array[i]);
      array.push(burst_time_array[i]);
      array.push(completion_time_array[i]);
      array.push(turnaround_time_array[i]);
      array.push(waiting_time_array[i]);
      data.push(array);
   }

   return (
      <div className=" flex flex-col gap-2 px-4 overflow-y-scroll">
         <table className="table-fixed w-[50rem] sm:w-full">
            <thead className="flex items-center justify-center bg-slate-200">
               <td className="w-1/2 px-2 py-1 sm:px-4 sm:py-2 flex items-center justify-center">ID</td>
               <td className="w-1/2 px-2 py-1 sm:px-4 sm:py-2 flex items-center justify-center">AT</td>
               <td className="w-1/2 px-2 py-1 sm:px-4 sm:py-2 flex items-center justify-center">BT</td>
               <td className="w-1/2 px-2 py-1 sm:px-4 sm:py-2 flex items-center justify-center">CT</td>
               <td className="w-1/2 px-2 py-1 sm:px-4 sm:py-2 flex items-center justify-center">TT</td>
               <td className="w-1/2 px-2 py-1 sm:px-4 sm:py-2 flex items-center justify-center">WT</td>
            </thead>
            <tbody className="border divide-y-[0.05rem] divide-slate-200">
               {
                  data.map((item) => {
                     return (
                        <tr className="flex items-center justify-center divide-x-[0.05rem] divide-slate-200" key={item[0]}>
                           <td className="w-1/2 px-2 py-1 sm:px-4 sm:py-2 flex items-center justify-center">
                              {item[0]}
                           </td>
                           <td className="w-1/2 px-2 py-1  sm:px-4 sm:py-2 flex items-center justify-center">
                              {item[1]}
                           </td>
                           <td className="w-1/2 px-2 py-1  sm:px-4 sm:py-2 flex items-center justify-center">
                              {item[2]}
                           </td>
                           <td className="w-1/2 px-2 py-1 sm:px-4 sm:py-2 flex items-center justify-center">
                              {item[3]}
                           </td>
                           <td className="w-1/2 px-2 py-1 sm:px-4 sm:py-2 flex items-center justify-center">
                              {item[4]}
                           </td>
                           <td className="w-1/2 px-2 py-1 sm:px-4 sm:py-2 flex items-center justify-center">
                              {item[5]}
                           </td>
                        </tr>
                     )
                  })
               }
            </tbody>
         </table>
      </div>
   );
}

export default Table;
