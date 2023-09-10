import React, { useLayoutEffect, useState } from "react";

const Customers = () => {
  const [customers, setCustomers] = useState([]);

  useLayoutEffect(() => {
    const getCustomers = async () => {
      const res = await fetch("/api/customers");
      const customers = await res.json();
      setCustomers(customers);
    };
    getCustomers().catch((e) => {
      console.log("error fetching customers: " + e);
    });
  });
  return (
    <table>
      <thead>
        <th>Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email Address</th>
        <th>Phone Number</th>
        <th>Address</th>
      </thead>
      <tbody>
        {customers.map((customer) => {
          const {
            customerId,
            firstName,
            lastName,
            emailAddress,
            phoneNumber,
            address,
          } = customer;
          return (
            <tr key={customerId}>
              <td>{customerId}</td>
              <td>{firstName}</td>
              <td>{lastName}</td>
              <td>{emailAddress}</td>
              <td>{phoneNumber}</td>
              <td>{address}</td>
            </tr>
          );
        })}
      </tbody>
    </table>
  );
};

export default Customers;
