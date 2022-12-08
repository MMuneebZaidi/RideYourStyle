-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 08, 2022 at 04:58 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rideyourstyle`
--

-- --------------------------------------------------------

--
-- Table structure for table `bentley`
--

CREATE TABLE `bentley` (
  `Name` varchar(25) NOT NULL,
  `Engine` varchar(25) NOT NULL,
  `Transmission Type` varchar(25) NOT NULL,
  `Engine Power` varchar(25) NOT NULL,
  `Top Speed` varchar(25) NOT NULL,
  `Acceleration` varchar(25) NOT NULL,
  `Mileage` varchar(25) NOT NULL,
  `Fuel Type` varchar(25) NOT NULL,
  `Fuel Tank` varchar(25) NOT NULL,
  `Body Type` varchar(25) NOT NULL,
  `Seating Capacity` varchar(25) NOT NULL,
  `Doors` varchar(25) NOT NULL,
  `Wheel Size` varchar(25) NOT NULL,
  `Convertible` varchar(25) NOT NULL,
  `Rating` varchar(25) NOT NULL,
  `Price` varchar(25) NOT NULL,
  `Stock` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bentley`
--

INSERT INTO `bentley` (`Name`, `Engine`, `Transmission Type`, `Engine Power`, `Top Speed`, `Acceleration`, `Mileage`, `Fuel Type`, `Fuel Tank`, `Body Type`, `Seating Capacity`, `Doors`, `Wheel Size`, `Convertible`, `Rating`, `Price`, `Stock`) VALUES
('Bentley Bentayga', '3956 cc', 'Auto', '550hp', '305', '4.4s', '7 to 10', 'Petrol', '22.4g', 'SUV', '7', '4', '285x45 R21', 'No', '7.5', '20060000', '0'),
('Bentley Continental GT', '3933 cc', 'Auto', '500hp', '336', '3.5s', '7 to 11', 'Petrol', '23.7g', 'Coupe', '4', '4', '265/40ZR21', 'Yes', '10', '18070000', '0'),
('Bentley Flying Spur', '5950 cc', 'Auto', '626hp', '333', '3.7s', '10 to 12', 'Petrol', '19.8g', 'Sedan', '5', '4', '295/40RY', 'No', '10', '15030000', '0'),
('Bentley Mulsanne', '6752 cc', 'Auto', '537hp', '290', '5.1s', '4 to 9', 'Petrol', '25.3g', 'Sedan', '5', '4', '265/45 ZR20', 'Yes', '8', '11065000', '0');

-- --------------------------------------------------------

--
-- Table structure for table `bmw`
--

CREATE TABLE `bmw` (
  `Name` varchar(25) NOT NULL,
  `Engine` varchar(25) NOT NULL,
  `Transmission Type` varchar(25) NOT NULL,
  `Engine Power` varchar(25) NOT NULL,
  `Top Speed` varchar(25) NOT NULL,
  `Acceleration` varchar(25) NOT NULL,
  `Mileage` varchar(25) NOT NULL,
  `Fuel Type` varchar(25) NOT NULL,
  `Fuel Tank` varchar(25) NOT NULL,
  `Body Type` varchar(25) NOT NULL,
  `Seating Capacity` varchar(25) NOT NULL,
  `Doors` varchar(25) NOT NULL,
  `Wheel Size` varchar(25) NOT NULL,
  `Convertible` varchar(25) NOT NULL,
  `Rating` varchar(25) NOT NULL,
  `Price` varchar(25) NOT NULL,
  `Stock` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bmw`
--

INSERT INTO `bmw` (`Name`, `Engine`, `Transmission Type`, `Engine Power`, `Top Speed`, `Acceleration`, `Mileage`, `Fuel Type`, `Fuel Tank`, `Body Type`, `Seating Capacity`, `Doors`, `Wheel Size`, `Convertible`, `Rating`, `Price`, `Stock`) VALUES
('BMW 740i', '2998 cc', 'Auto', '523hp', '250', '5.45s', '9 to 12', 'Petrol', '20.6g', 'Sedan', '5', '4', '245/45ZR19', 'Yes', '8.5', '18050000', '0'),
('BMW m850i', '4395 cc', 'Auto', '523hp', '250', '3.3s', '8 to 14', 'Petrol', '18g', 'Sedan', '5', '4', '245/35R20', 'Yes', '8.8', '19000000', '0'),
('BMW X7', '2998 cc', 'Auto', '630hp', '245', '6.1s', '11 to 13', 'Diesel', '21.9g', 'SUV', '7', '4', '275/50R20', 'No', '9.5', '20500000', '0');

-- --------------------------------------------------------

--
-- Table structure for table `chevrolet`
--

CREATE TABLE `chevrolet` (
  `Name` varchar(25) NOT NULL,
  `Engine` varchar(25) NOT NULL,
  `Transmission Type` varchar(25) NOT NULL,
  `Engine Power` varchar(25) NOT NULL,
  `Top Speed` varchar(25) NOT NULL,
  `Acceleration` varchar(25) NOT NULL,
  `Mileage` varchar(25) NOT NULL,
  `Fuel Type` varchar(25) NOT NULL,
  `Fuel Tank` varchar(25) NOT NULL,
  `Body Type` varchar(25) NOT NULL,
  `Seating Capacity` varchar(25) NOT NULL,
  `Doors` varchar(25) NOT NULL,
  `Wheel Size` varchar(25) NOT NULL,
  `Convertible` varchar(25) NOT NULL,
  `Rating` varchar(25) NOT NULL,
  `Price` varchar(25) NOT NULL,
  `Stock` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chevrolet`
--

INSERT INTO `chevrolet` (`Name`, `Engine`, `Transmission Type`, `Engine Power`, `Top Speed`, `Acceleration`, `Mileage`, `Fuel Type`, `Fuel Tank`, `Body Type`, `Seating Capacity`, `Doors`, `Wheel Size`, `Convertible`, `Rating`, `Price`, `Stock`) VALUES
('Chevrolet Camaro', '3564 cc', 'Manual', '275hp', '290', '5.6s', '8 to 12', 'Petrol', '19g', 'Coupe', '4', '2', '245/50R18', 'Yes', '8', '16900000', '0'),
('Chevrolet Corvette', '6161 cc', 'Auto', '490hp', '312', '2.6s', '6 to 11', 'Petrol', '18.5g', 'Coupe', '2', '2', '245/35ZR19', 'Yes', '10', '25055000', '0'),
('Chevrolet Cruze', '1998 cc', 'Manual', '153hp', '201', '8.4s', '15 to 18', 'Diesel', '15.8g', 'Sedan', '5', '4', '215/50R17', 'Yes', '8', '12000000', '0'),
('Chevrolet Trailblazer', '2776 cc', 'Auto', '197hp', '191 ', '9.4s', '12 to 14', 'Diesel', '20g', 'SUV', '7', '5', '265/60R18', 'No', '7.5', '19050000', '0');

-- --------------------------------------------------------

--
-- Table structure for table `mercedes`
--

CREATE TABLE `mercedes` (
  `Name` varchar(25) NOT NULL,
  `Engine` varchar(25) NOT NULL,
  `Transmission Type` varchar(25) NOT NULL,
  `Engine Power` varchar(25) NOT NULL,
  `Top Speed` varchar(25) NOT NULL,
  `Acceleration` varchar(25) NOT NULL,
  `Mileage` varchar(25) NOT NULL,
  `Fuel Type` varchar(25) NOT NULL,
  `Fuel Tank` varchar(25) NOT NULL,
  `Body Type` varchar(25) NOT NULL,
  `Seating Capacity` varchar(25) NOT NULL,
  `Doors` varchar(25) NOT NULL,
  `Wheel Size` varchar(25) NOT NULL,
  `Convertible` varchar(25) NOT NULL,
  `Rating` varchar(25) NOT NULL,
  `Price` varchar(25) NOT NULL,
  `Stock` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mercedes`
--

INSERT INTO `mercedes` (`Name`, `Engine`, `Transmission Type`, `Engine Power`, `Top Speed`, `Acceleration`, `Mileage`, `Fuel Type`, `Fuel Tank`, `Body Type`, `Seating Capacity`, `Doors`, `Wheel Size`, `Convertible`, `Rating`, `Price`, `Stock`) VALUES
('Mercedes Benz E350', '1991 cc', 'Auto', '255hp', '232', '6.1s', '10 to 13', 'Petrol', '17.4g', 'Sedan', '5', '4', '225/55 R17', 'Yes', '8.4', '19075000', '0'),
('Mercedes Benz s500', '2999 cc', 'Auto', '439hp', '429', '4.9s', '9 to 13', 'Petrol', '16.1g', 'Sedan', '5', '4', '255/45R19', 'Yes', '9', '20025000', '0'),
('Mercedes Maybach S680', '5980 cc', 'Auto', '621hp', '250', '4.5s', '5 to 9', 'Petrol', '22.2', 'Sedan', '5', '4', '255/40R20', 'Yes', '8.3', '25050000', '0');

-- --------------------------------------------------------

--
-- Table structure for table `porsche`
--

CREATE TABLE `porsche` (
  `Name` varchar(25) NOT NULL,
  `Engine` varchar(25) NOT NULL,
  `Transmission Type` varchar(25) NOT NULL,
  `Engine Power` varchar(25) NOT NULL,
  `Top Speed` varchar(25) NOT NULL,
  `Acceleration` varchar(25) NOT NULL,
  `Mileage` varchar(25) NOT NULL,
  `Fuel Type` varchar(25) NOT NULL,
  `Fuel Tank` varchar(25) NOT NULL,
  `Body Type` varchar(25) NOT NULL,
  `Seating Capacity` varchar(25) NOT NULL,
  `Doors` varchar(25) NOT NULL,
  `Wheel Size` varchar(25) NOT NULL,
  `Convertible` varchar(25) NOT NULL,
  `Rating` varchar(25) NOT NULL,
  `Price` varchar(25) NOT NULL,
  `Stock` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `porsche`
--

INSERT INTO `porsche` (`Name`, `Engine`, `Transmission Type`, `Engine Power`, `Top Speed`, `Acceleration`, `Mileage`, `Fuel Type`, `Fuel Tank`, `Body Type`, `Seating Capacity`, `Doors`, `Wheel Size`, `Convertible`, `Rating`, `Price`, `Stock`) VALUES
('Porsche Panamera', '4806 cc', 'Auto', '394hp', '305', '4.1s', '5 to 8', 'Petrol', '17.5g', 'Sedan', '5', '4', '275/45 ZR18', 'No', '8', '24010000', '0'),
('Porsche Cayenne', '3996 cc', 'Auto', '394hp', '305', '4.1s', '9 to 11', 'Petrol', '19.7g', 'SUV', '5', '5', '315/35 R 21', 'No', '9.5', '18590000', '0'),
('Porsche Boxster S', '3436 cc', 'Auto', '311hp', '280', '5.2s', '9 to 14', 'Petrol', '14g', 'Coupe', '2', '2', '265/40/R19', 'Yes', '10', '30000000', '0');

-- --------------------------------------------------------

--
-- Table structure for table `rollsroyce`
--

CREATE TABLE `rollsroyce` (
  `Name` varchar(25) NOT NULL,
  `Engine` varchar(25) NOT NULL,
  `Transmission Type` varchar(25) NOT NULL,
  `Engine Power` varchar(25) NOT NULL,
  `Top Speed` varchar(25) NOT NULL,
  `Acceleration` varchar(25) NOT NULL,
  `Mileage` varchar(25) NOT NULL,
  `Fuel Type` varchar(25) NOT NULL,
  `Fuel Tank` varchar(25) NOT NULL,
  `Body Type` varchar(25) NOT NULL,
  `Seating Capacity` varchar(25) NOT NULL,
  `Doors` varchar(25) NOT NULL,
  `Wheel Size` varchar(25) NOT NULL,
  `Convertible` varchar(25) NOT NULL,
  `Rating` varchar(25) NOT NULL,
  `Price` varchar(25) NOT NULL,
  `Stock` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rollsroyce`
--

INSERT INTO `rollsroyce` (`Name`, `Engine`, `Transmission Type`, `Engine Power`, `Top Speed`, `Acceleration`, `Mileage`, `Fuel Type`, `Fuel Tank`, `Body Type`, `Seating Capacity`, `Doors`, `Wheel Size`, `Convertible`, `Rating`, `Price`, `Stock`) VALUES
('Rollsroyce Phantom', '6749 cc', 'Auto', '523hp', '250', '5s', '5 to 9', 'Petrol', '26.4g', 'Sedan', '5', '4', '285/45R21', 'Yes', '10', '22500000', '0'),
('Rollsroyce Dawn', '6598 cc', 'Auto', '563hp', '250', '4.8s', '5 to 8', 'Petrol', '21.6g', 'Sedan', '4', '4', '255/45R20', 'Yes', '9.5', '19500000', '0'),
('Rollsroyce Cullinan', '6750 cc', 'Auto', '563hp', '250', '5.2s', '5 to 9', 'Petrol', '23.8g', 'SUV', '7', '4', '255/45YR22', 'No', '7.5', '18050000', '0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bentley`
--
ALTER TABLE `bentley`
  ADD PRIMARY KEY (`Name`);

--
-- Indexes for table `bmw`
--
ALTER TABLE `bmw`
  ADD PRIMARY KEY (`Name`);

--
-- Indexes for table `chevrolet`
--
ALTER TABLE `chevrolet`
  ADD PRIMARY KEY (`Name`);

--
-- Indexes for table `mercedes`
--
ALTER TABLE `mercedes`
  ADD PRIMARY KEY (`Name`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
