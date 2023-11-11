-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 11, 2023 at 01:37 AM
-- Server version: 10.6.15-MariaDB-cll-lve
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u621395494_tourism_agency`
--

-- --------------------------------------------------------

--
-- Table structure for table `features`
--

CREATE TABLE `features` (
  `id` int(11) NOT NULL,
  `type` enum('tesis ozelligi','oda ozelligi','pansiyon ozelligi','sezon ozellikleri') DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `features`
--

INSERT INTO `features` (`id`, `type`, `name`) VALUES
(1, 'tesis ozelligi', 'Kasa'),
(2, 'tesis ozelligi', 'Ücretsiz WiFi'),
(3, 'tesis ozelligi', 'Yüzme Havuzu'),
(4, 'tesis ozelligi', 'Fitness Center'),
(5, 'tesis ozelligi', 'Hotel Concierge'),
(6, 'tesis ozelligi', 'SPA'),
(7, 'tesis ozelligi', '7/24 Oda Servisi'),
(8, 'pansiyon ozelligi', 'Ultra Herşey Dahil'),
(9, 'pansiyon ozelligi', 'Herşey Dahil'),
(10, 'pansiyon ozelligi', 'Oda Kahvaltı'),
(11, 'pansiyon ozelligi', 'Tam Pansiyon'),
(12, 'pansiyon ozelligi', 'Yarım Pansiyon'),
(13, 'pansiyon ozelligi', 'Sadece Yatak'),
(14, 'pansiyon ozelligi', 'Alkol Hariç Full credit'),
(15, 'sezon ozellikleri', 'Kis Sezonu'),
(16, 'sezon ozellikleri', 'Yaz Sezonu'),
(17, 'oda ozelligi', 'Televizyon'),
(18, 'oda ozelligi', 'Minibar'),
(19, 'oda ozelligi', 'Oyun Konsolu'),
(20, 'oda ozelligi', 'Kasa'),
(21, 'oda ozelligi', 'Projeksiyon');

-- --------------------------------------------------------

--
-- Table structure for table `lodgings`
--

CREATE TABLE `lodgings` (
  `id` int(11) NOT NULL,
  `otel_id` int(11) NOT NULL,
  `type` enum('Ultra Herşey Dahil','Herşey Dahil','Oda Kahvaltı','Tam Pansiyon','Yarım Pansiyon','Sadece Yatak','Alkol Hariç Full Credit') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `lodgings`
--

INSERT INTO `lodgings` (`id`, `otel_id`, `type`) VALUES
(1, 1, 'Ultra Herşey Dahil'),
(2, 1, 'Ultra Herşey Dahil'),
(6, 2, 'Oda Kahvaltı'),
(7, 9, 'Ultra Herşey Dahil');

-- --------------------------------------------------------

--
-- Table structure for table `otel`
--

CREATE TABLE `otel` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `region` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `e-mail` varchar(255) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `star` int(11) NOT NULL,
  `features` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `otel`
--

INSERT INTO `otel` (`id`, `name`, `region`, `city`, `address`, `e-mail`, `phone`, `star`, `features`) VALUES
(1, 'Istanbuk', 'Avcilar', 'Istanbul', 'Istanbul-Avcilar', 'bkarapelit@gmail.com', '5443332211', 3, 'Wifi Free'),
(2, 'Hilton', 'Adana', 'Seyhan', 'Seyhan-Adana', 'hilton@hilton.com.tr', '5334448811', 3, 'Wifi Free, Otopark'),
(3, 'Demo', 'Demo', 'Demo', 'Demo-Demo', 'Demo', 'Demo', 3, '[]'),
(4, 'Hilton Bursa Convention Center & Spa', 'Bursa', 'Osmangazi', 'Soğanlı, İstanbul Cd No:347, 16210 Osmangazi/Bursa', ' bursa.sales@hilton.com', '02245000505', 5, 'Kasa, Ücretsiz WiFi, Yüzme Havuzu, Fitness Center, SPA, Hotel Concierge, 7/24 Oda Servisi'),
(9, 'Istanbul', 'Antalya', 'Antalya', 'Antalya - Serik ', 'istanbul@antalya.com', '544778812', 3, 'Ücretsiz WiFi, 7/24 Oda Servisi');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `id` int(11) NOT NULL,
  `otel_id` int(11) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `start_date` varchar(255) DEFAULT NULL,
  `end_date` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `otel_id` int(11) NOT NULL,
  `lodgings_id` int(11) NOT NULL,
  `season_id` int(11) NOT NULL,
  `price_child` int(11) NOT NULL,
  `features` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `stock` int(11) NOT NULL,
  `bed_number` int(11) DEFAULT NULL,
  `sqr_meter` int(11) DEFAULT NULL,
  `room_type` enum('SINGLE','DOUBLE','TRIPLE','FAMILY','KING','DISABLED','JUNIOR','DUBLEX','SUIT') DEFAULT NULL,
  `price_adult` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`id`, `otel_id`, `lodgings_id`, `season_id`, `price_child`, `features`, `name`, `stock`, `bed_number`, `sqr_meter`, `room_type`, `price_adult`) VALUES
(3, 1, 1, 2, 750, 'Televizyon, Minibar, Oyun Konsolu', 'Tek_Kişilik Oda', 12, 8, 120, 'SINGLE', 1300),
(4, 1, 1, 2, 2, 'Minibar', 'Tek_Kişilik Oda', 12, 8, 554, 'SINGLE', 213);

-- --------------------------------------------------------

--
-- Table structure for table `season`
--

CREATE TABLE `season` (
  `id` int(11) NOT NULL,
  `otel_id` int(11) NOT NULL,
  `start_date` varchar(255) NOT NULL,
  `end_date` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `season`
--

INSERT INTO `season` (`id`, `otel_id`, `start_date`, `end_date`, `name`) VALUES
(1, 4, '432424', '423423', 'Kis Sezonu'),
(2, 1, '5785', '55875', 'Kis Sezonu'),
(3, 2, '01/06/2023', '30/12/2023', 'Yaz Sezonu');

-- --------------------------------------------------------

--
-- Table structure for table `tourism_agency_user`
--

CREATE TABLE `tourism_agency_user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `type` enum('admin','employee') NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `tourism_agency_user`
--

INSERT INTO `tourism_agency_user` (`id`, `username`, `password`, `type`, `name`) VALUES
(1, 'elifT', '12345', 'admin', 'Elif Tunbul'),
(2, 'bkarapelit', '1234789', 'employee', 'Baris Karapelit'),
(3, 'yIgde', '123456', 'admin', 'Yagmur Igde'),
(4, 'tAydin', '012345', 'employee', 'Tuba Aydin'),
(5, 't', 't', 'employee', 't'),
(6, 'baris', '01234', 'admin', 'ewqq'),
(7, 'erwqeqw', 'ewqe', 'admin', 'ewqq'),
(9, 'a', 'a', 'admin', 'a');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `features`
--
ALTER TABLE `features`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lodgings`
--
ALTER TABLE `lodgings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `otel`
--
ALTER TABLE `otel`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `season`
--
ALTER TABLE `season`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tourism_agency_user`
--
ALTER TABLE `tourism_agency_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `features`
--
ALTER TABLE `features`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `lodgings`
--
ALTER TABLE `lodgings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `otel`
--
ALTER TABLE `otel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `season`
--
ALTER TABLE `season`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tourism_agency_user`
--
ALTER TABLE `tourism_agency_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
