-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 01, 2023 at 10:01 AM
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
-- Database: `admin/user`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Username` varchar(64) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `CNIC` varchar(13) NOT NULL,
  `Phone Number` varchar(11) NOT NULL,
  `Age` int(2) NOT NULL,
  `City` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `Name`, `Username`, `Email`, `Password`, `CNIC`, `Phone Number`, `Age`, `City`) VALUES
(1, 'Muneeb Zaidi', 'muneebzaidi', 'muneebzaidi12345@gmail.com', 'dsaqw_2018', '3520262361115', '03189464829', 19, 'Lahore');

-- --------------------------------------------------------

--
-- Table structure for table `garage`
--

CREATE TABLE `garage` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `car1` varchar(25) DEFAULT NULL,
  `car2` varchar(25) DEFAULT NULL,
  `car3` varchar(25) DEFAULT NULL,
  `car4` varchar(25) DEFAULT NULL,
  `car5` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `garage`
--

INSERT INTO `garage` (`id`, `user_id`, `car1`, `car2`, `car3`, `car4`, `car5`) VALUES
(1, 6, NULL, NULL, NULL, NULL, NULL),
(2, 9, NULL, NULL, NULL, NULL, NULL),
(3, 7, NULL, NULL, NULL, NULL, NULL),
(33, 22, NULL, NULL, NULL, NULL, NULL),
(35, 24, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pendings`
--

CREATE TABLE `pendings` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `Listed` varchar(256) DEFAULT NULL,
  `Status` varchar(25) DEFAULT NULL,
  `Managed By` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sell/purchase`
--

CREATE TABLE `sell/purchase` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `Listed` varchar(256) DEFAULT NULL,
  `Status` varchar(25) DEFAULT NULL,
  `Managed By` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sell/purchase`
--

INSERT INTO `sell/purchase` (`id`, `user_id`, `Listed`, `Status`, `Managed By`) VALUES
(14, 7, 'Porsche 911\nPorsche Cayenne\n', 'Accepted', 'muneebzaidi12345@gmail.com'),
(15, 7, 'BMW m4\n', 'Rejected', 'muneebzaidi12345@gmail.com'),
(16, 9, 'Rollsroyce Dawn\nRollsRoyce Wraith\nBentley Arnage\n', 'Accepted', 'muneebzaidi12345@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Username` varchar(25) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `CNIC` varchar(13) NOT NULL,
  `Phone Number` varchar(11) NOT NULL,
  `Age` int(2) NOT NULL,
  `City` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `Name`, `Username`, `Email`, `Password`, `CNIC`, `Phone Number`, `Age`, `City`) VALUES
(6, 'Ali', 'alihamza', 'ali@gmail.com', 'okMuneeb', '3274613431123', '03138278944', 23, 'Lahore'),
(7, 'Test Unit', 'test', 'test@test.com', 'dsaqw_123', '5465757678678', '89868838686', 19, 'Lahore'),
(9, 'Muneeb', 'muneebzaiodi', 'muneebzaidi@gmail.com', 'okMuneeb', '3892389283123', '03189464829', 19, 'Lahore'),
(22, 'Hamza', 'hamza', 'hamza@gmail.com', 'Dsaqw_123', '3520245324442', '03983437853', 19, 'Lahore'),
(24, 'King', 'king', 'king@king.com', 'Dsaqw_123', '3520212345432', '03181234567', 19, 'Lahore');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `garage`
--
ALTER TABLE `garage`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user` (`user_id`);

--
-- Indexes for table `pendings`
--
ALTER TABLE `pendings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `sell/purchase`
--
ALTER TABLE `sell/purchase`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`,`Email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `garage`
--
ALTER TABLE `garage`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `pendings`
--
ALTER TABLE `pendings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `sell/purchase`
--
ALTER TABLE `sell/purchase`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `garage`
--
ALTER TABLE `garage`
  ADD CONSTRAINT `garage_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `pendings`
--
ALTER TABLE `pendings`
  ADD CONSTRAINT `pendings_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `sell/purchase`
--
ALTER TABLE `sell/purchase`
  ADD CONSTRAINT `sell/purchase_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
