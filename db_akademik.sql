-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 03, 2025 at 08:42 AM
-- Server version: 11.8.3-MariaDB
-- PHP Version: 8.4.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_akademik`
--

-- --------------------------------------------------------

--
-- Table structure for table `DETIL_KRS`
--

CREATE TABLE `DETIL_KRS` (
  `TA` varchar(9) NOT NULL,
  `Semester` varchar(8) NOT NULL,
  `NIM` char(10) NOT NULL,
  `KodeMTK` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `KRS`
--

CREATE TABLE `KRS` (
  `TA` varchar(9) NOT NULL,
  `Semester` varchar(8) NOT NULL,
  `NIM` char(10) NOT NULL,
  `Tgl_KRS` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Mahasiswa`
--

CREATE TABLE `Mahasiswa` (
  `NIM` char(10) NOT NULL,
  `Nama` varchar(50) DEFAULT NULL,
  `Alamat` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Dumping data for table `Mahasiswa`
--

INSERT INTO `Mahasiswa` (`NIM`, `Nama`, `Alamat`) VALUES
('2311500186', 'Cihuy', 'Jl. raya\r\n'),
('2311501759', 'Hidayat Ramadhani Supriyatna', 'Jl. jalan');

-- --------------------------------------------------------

--
-- Table structure for table `Matakuliah`
--

CREATE TABLE `Matakuliah` (
  `KodeMTK` char(5) NOT NULL,
  `NamaMTK` varchar(50) DEFAULT NULL,
  `SKS` tinyint(4) DEFAULT NULL,
  `KodePrasyarat` char(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `PERIODE`
--

CREATE TABLE `PERIODE` (
  `TA` varchar(9) NOT NULL,
  `Semester` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Dumping data for table `PERIODE`
--

INSERT INTO `PERIODE` (`TA`, `Semester`) VALUES
('2025-2026', 'GASAL'),
('2025-2026', 'GENAP'),
('2026-2027', 'GASAL'),
('2026-2027', 'GENAP');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `DETIL_KRS`
--
ALTER TABLE `DETIL_KRS`
  ADD PRIMARY KEY (`TA`,`Semester`,`NIM`,`KodeMTK`),
  ADD KEY `KodeMTK` (`KodeMTK`);

--
-- Indexes for table `KRS`
--
ALTER TABLE `KRS`
  ADD PRIMARY KEY (`TA`,`Semester`,`NIM`),
  ADD KEY `FK_KRS_Mahasiswa` (`NIM`);

--
-- Indexes for table `Mahasiswa`
--
ALTER TABLE `Mahasiswa`
  ADD PRIMARY KEY (`NIM`);

--
-- Indexes for table `Matakuliah`
--
ALTER TABLE `Matakuliah`
  ADD PRIMARY KEY (`KodeMTK`),
  ADD KEY `KodePrasyarat` (`KodePrasyarat`);

--
-- Indexes for table `PERIODE`
--
ALTER TABLE `PERIODE`
  ADD PRIMARY KEY (`TA`,`Semester`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `DETIL_KRS`
--
ALTER TABLE `DETIL_KRS`
  ADD CONSTRAINT `DETIL_KRS_ibfk_1` FOREIGN KEY (`TA`,`Semester`,`NIM`) REFERENCES `KRS` (`TA`, `Semester`, `NIM`),
  ADD CONSTRAINT `DETIL_KRS_ibfk_2` FOREIGN KEY (`KodeMTK`) REFERENCES `Matakuliah` (`KodeMTK`);

--
-- Constraints for table `KRS`
--
ALTER TABLE `KRS`
  ADD CONSTRAINT `FK_KRS_Mahasiswa` FOREIGN KEY (`NIM`) REFERENCES `Mahasiswa` (`NIM`),
  ADD CONSTRAINT `KRS_ibfk_1` FOREIGN KEY (`TA`,`Semester`) REFERENCES `PERIODE` (`TA`, `Semester`);

--
-- Constraints for table `Matakuliah`
--
ALTER TABLE `Matakuliah`
  ADD CONSTRAINT `Matakuliah_ibfk_1` FOREIGN KEY (`KodePrasyarat`) REFERENCES `Matakuliah` (`KodeMTK`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
