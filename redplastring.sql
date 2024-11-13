-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 02, 2024 at 12:48 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `redplastring`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_details`
--

CREATE TABLE `admin_details` (
  `id` int(11) NOT NULL,
  `user_id` varchar(128) NOT NULL,
  `email` varchar(225) NOT NULL,
  `password` varchar(225) NOT NULL,
  `name` varchar(128) NOT NULL,
  `profile_photo` varchar(225) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `mobile` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin_details`
--

INSERT INTO `admin_details` (`id`, `user_id`, `email`, `password`, `name`, `profile_photo`, `created_date`, `updated_date`, `mobile`) VALUES
(1, 'admin123', 'admin@yopmail.com', '$2a$10$5KGgDiA8MPZ1oRXmMXT2I.LVOlfzaeWiFiqRPw4ybvqTt89ful9Mi', 'Redplastering', 'abc.png', '2023-08-22 13:33:43', '2023-09-13 10:55:18', '9988776655');

-- --------------------------------------------------------

--
-- Table structure for table `emp_location`
--

CREATE TABLE `emp_location` (
  `id` int(11) NOT NULL,
  `project_number` int(11) NOT NULL,
  `project_name` varchar(225) NOT NULL,
  `address` text NOT NULL,
  `range_value` double NOT NULL,
  `job_site` varchar(128) NOT NULL,
  `latitude` varchar(64) NOT NULL,
  `longitude` varchar(64) NOT NULL,
  `shift_start_time` varchar(64) DEFAULT NULL,
  `shift_end_time` varchar(64) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `emp_location`
--

INSERT INTO `emp_location` (`id`, `project_number`, `project_name`, `address`, `range_value`, `job_site`, `latitude`, `longitude`, `shift_start_time`, `shift_end_time`, `deleted`, `created_date`, `updated_date`) VALUES
(1, 0, '', 'unassigned', 0, 'unassigned', '0', '0', NULL, NULL, 0, '2023-09-22 08:19:35', '2023-09-22 08:19:35'),
(26, 1, 'Test', 'HCL sector 126, Noida, Sector 126, Noida, Uttar Pradesh, India', 6, 'Noida', '28.5356469', '77.3437431', '08:30', '17:00', 1, '2023-09-05 13:13:40', '2024-04-03 08:01:36'),
(86, 0, 'test11', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 2, 'CTS', '28.6222274', '77.3830285', '10:30', '20:32', 1, '2023-09-18 11:59:53', '2024-04-03 08:01:36'),
(124, 0, '667', 'Tata Motors Limited, Sector 63 Road, D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'Tata', '28.6311401', '77.3824738', '17:00', '21:00', 1, '2023-09-19 12:23:17', '2024-04-03 08:01:36'),
(154, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'CTS', '28.6222274', '77.3830285', '16:00', '20:40', 1, '2023-09-25 11:16:37', '2024-04-03 08:01:36'),
(156, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'cts', '28.6222274', '77.3830285', '17:11', '19:13', 1, '2023-09-25 11:42:07', '2024-04-03 08:01:36'),
(158, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'cts', '28.6222274', '77.3830285', '17:32', '19:32', 1, '2023-09-25 12:02:35', '2024-04-03 12:30:57'),
(160, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'cts', '28.6222274', '77.3830285', '17:51', '18:30', 1, '2023-09-25 12:20:43', '2024-04-03 08:01:36'),
(163, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '18:03', '19:03', 1, '2023-09-25 12:32:25', '2024-04-03 08:01:36'),
(165, 0, '', 'Tata Motors Limited, Sector 63 Road, D Block, Sector 63, Noida, Uttar Pradesh, India', 10, 'Tata', '28.6311401', '77.3824738', '12:12', '20:30', 1, '2023-09-26 06:42:33', '2024-04-03 08:01:36'),
(172, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'CTS', '28.6222274', '77.3830285', '12:36', '20:30', 1, '2023-09-27 08:07:16', '2024-04-03 08:01:36'),
(179, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'CTS', '28.6222274', '77.3830285', '11:00', '20:30', 1, '2023-09-27 09:24:00', '2024-04-03 08:01:36'),
(185, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'cts', '28.6222274', '77.3830285', '16:07', '17:07', 1, '2023-09-28 10:37:56', '2024-04-03 08:01:36'),
(187, 1, 'abc', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'CTS', '28.6222274', '77.3830285', '11:00', '20:32', 0, '2023-09-28 11:02:38', '2024-04-03 08:01:36'),
(189, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'CTS', '28.6222274', '77.3830285', '11:00', '20:30', 1, '2023-09-28 12:44:38', '2024-04-03 08:01:36'),
(191, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 5, 'CTS', '28.6222274', '77.3830285', '11:00', '21:00', 1, '2023-09-28 13:49:21', '2024-04-03 08:01:36'),
(193, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 7, 'CTS', '28.6222274', '77.3830285', '12:10', '21:00', 1, '2023-09-29 06:40:24', '2024-04-03 08:01:36'),
(198, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'cts', '28.6222274', '77.3830285', '14:58', '15:58', 1, '2023-10-04 09:28:14', '2024-04-03 08:01:36'),
(200, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'cts', '28.6222274', '77.3830285', '15:52', '16:52', 1, '2023-10-04 10:22:25', '2024-04-03 08:01:36'),
(202, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 5, 'CTS', '28.6222274', '77.3830285', '11:00', '20:30', 1, '2023-10-04 10:26:00', '2024-04-03 08:01:36'),
(205, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 5, 'CTS', '28.6222274', '77.3830285', '11:06', '21:10', 1, '2023-10-04 10:36:43', '2024-04-03 08:01:36'),
(206, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'cts', '28.6222274', '77.3830285', '16:08', '17:08', 1, '2023-10-04 10:38:05', '2024-04-03 08:01:36'),
(212, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'cts', '28.6222274', '77.3830285', '16:51', '17:51', 1, '2023-10-04 11:21:37', '2024-04-03 08:01:36'),
(214, 0, '', 'Amrapali Silicon City Gate no. 1, Amarpali Silicon City, Sector 76, Noida, Uttar Pradesh, India', 10, 'Test 1', '28.5677687', '77.3798237', '15:00', '19:55', 0, '2023-10-04 11:25:18', '2024-04-03 08:01:36'),
(215, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 9, 'cts', '28.6222274', '77.3830285', '16:59', '17:58', 0, '2023-10-04 11:29:11', '2024-04-03 08:01:36'),
(247, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'CTS', '28.6222274', '77.3830285', '15:42', '22:42', 1, '2023-10-06 11:12:57', '2024-04-03 08:01:36'),
(250, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 7, 'CTS', '28.6222274', '77.3830285', '15:30', '21:30', 1, '2023-10-06 11:55:02', '2024-04-03 08:01:36'),
(252, 0, '', 'New Little Star Public School, Shradhapuri Phase 1, Meerut, Uttar Pradesh, India', 8, 'Nimisha', '28.9652019', '77.6937179', '14:00', '22:00', 0, '2023-10-06 13:24:16', '2023-12-19 10:25:50'),
(254, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 7, 'CTS', '28.6222274', '77.3830285', '10:30', '21:30', 1, '2023-10-06 14:08:04', '2024-04-03 08:01:36'),
(256, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 7, 'CTS', '28.6222274', '77.3830285', '11:32', '21:30', 1, '2023-10-09 07:02:50', '2024-04-03 08:01:36'),
(258, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'Noida', '28.6222274', '77.3830285', '11:30', '20:00', 1, '2023-10-09 07:41:50', '2024-04-03 08:01:36'),
(280, 0, '', 'Amrapali Silicon City Gate no. 1, Amarpali Silicon City, Sector 76, Noida, Uttar Pradesh, India', 8, 'Work', '28.5677687', '77.3798237', '16:30', '18:30', 0, '2023-10-12 11:05:48', '2024-04-03 08:01:36'),
(312, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'cts', '28.6222274', '77.3830285', '19:04', '20:04', 0, '2023-10-12 13:34:18', '2024-04-03 08:01:36'),
(320, 0, '', 'Amrapali Silicon City Gate no. 1, Amarpali Silicon City, Sector 76, Noida, Uttar Pradesh, India', 15, 'Work', '28.5677687', '77.3798237', '07:27', '19:27', 0, '2023-10-12 13:57:25', '2024-04-03 08:01:36'),
(352, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '19:53', '20:53', 0, '2023-10-12 14:23:56', '2024-04-03 08:01:36'),
(379, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '11:56', '12:56', 0, '2023-10-13 06:26:17', '2024-04-03 08:01:36'),
(384, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 9, 'cts', '28.6222274', '77.3830285', '12:07', '13:07', 0, '2023-10-13 06:37:20', '2024-04-03 08:01:36'),
(391, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '13:15', '14:15', 0, '2023-10-13 07:45:54', '2024-04-03 08:01:36'),
(398, 0, '', 'Amrapali Silicon City Gate no. 1, Amarpali Silicon City, Sector 76, Noida, Uttar Pradesh, India', 10, 'Work', '28.5677687', '77.3798237', '06:03', '18:00', 0, '2023-10-13 08:28:17', '2024-04-03 08:01:36'),
(423, 0, '', 'New Little Star Public School, Shradhapuri Phase 1, Meerut, Uttar Pradesh, India', 6, 'Nimisha', '28.9652019', '77.6937179', '11:00', '20:00', 0, '2023-10-13 11:09:05', '2024-04-03 08:01:36'),
(495, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 4, 'cts', '28.6222274', '77.3830285', '20:15', '21:15', 0, '2023-10-13 14:45:12', '2024-04-03 08:01:36'),
(502, 0, '', 'Amrapali Silicon City Gate no. 1, Amarpali Silicon City, Sector 76, Noida, Uttar Pradesh, India', 10, 'Work', '28.5677687', '77.3798237', '14:00', '19:01', 0, '2023-10-16 08:31:36', '2024-04-03 08:01:36'),
(520, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 1, 'Office', '28.6222274', '77.3830285', '10:00', '22:00', 0, '2023-10-16 09:14:42', '2024-04-03 08:01:36'),
(560, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 1, 'Office', '28.6222274', '77.3830285', '10:00', '22:00', 1, '2023-10-16 09:36:39', '2024-04-03 08:01:36'),
(622, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 9, 'cts', '28.6222274', '77.3830285', '18:08', '19:08', 0, '2023-10-16 12:38:58', '2024-04-03 08:01:36'),
(629, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '18:15', '19:15', 0, '2023-10-16 12:45:51', '2024-04-03 08:01:36'),
(633, 0, '', 'Gaur City Mall, Greater Noida West Road, Gaur City 1, Sector 4, Greater Noida, Ghaziabad, Uttar Pradesh, India', 1, 'Gaur City Mall', '28.6060285', '77.4296325', '10:00', '22:00', 0, '2023-10-16 12:59:33', '2024-04-03 08:01:36'),
(659, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 7, 'cts', '28.6222274', '77.3830285', '19:55', '20:54', 0, '2023-10-16 14:24:45', '2024-04-03 08:01:36'),
(660, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 7, 'cts', '28.6222274', '77.3830285', '19:54', '20:54', 0, '2023-10-16 14:24:46', '2024-04-03 08:01:36'),
(718, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 9, 'cts', '28.6222274', '77.3830285', '16:07', '17:07', 0, '2023-10-17 10:37:57', '2024-04-03 08:01:36'),
(723, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 5, 'cts', '28.6222274', '77.3830285', '16:13', '17:13', 0, '2023-10-17 10:43:56', '2024-04-03 08:01:36'),
(728, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 7, 'cts', '28.6222274', '77.3830285', '16:28', '17:28', 0, '2023-10-17 10:58:56', '2024-04-03 08:01:36'),
(736, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 7, 'cts', '28.6222274', '77.3830285', '16:59', '17:59', 0, '2023-10-17 11:30:04', '2024-04-03 08:01:36'),
(742, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 9, 'cts', '28.6222274', '77.3830285', '17:17', '19:17', 0, '2023-10-17 11:47:33', '2024-04-03 08:01:36'),
(747, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '17:40', '18:40', 0, '2023-10-17 12:10:12', '2024-04-03 08:01:36'),
(871, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '12:11', '13:11', 0, '2023-10-18 06:41:28', '2024-04-03 08:01:36'),
(1014, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '12:29', '13:29', 1, '2023-10-18 06:59:33', '2024-04-03 08:01:36'),
(1089, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 9, 'cts', '28.6222274', '77.3830285', '12:35', '13:35', 0, '2023-10-18 07:05:37', '2024-04-03 08:01:36'),
(1094, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '12:45', '13:45', 0, '2023-10-18 07:15:52', '2024-04-03 08:01:36'),
(1102, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '13:01', '14:01', 0, '2023-10-18 07:31:52', '2024-04-03 08:01:36'),
(1108, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '13:30', '14:30', 0, '2023-10-18 08:00:42', '2024-04-03 08:01:36'),
(1115, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '13:36', '14:36', 0, '2023-10-18 08:06:54', '2024-04-03 08:01:36'),
(1126, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '14:42', '15:49', 0, '2023-10-18 08:19:23', '2024-04-03 08:01:36'),
(1162, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'cts', '28.6222274', '77.3830285', '15:30', '16:29', 0, '2023-10-18 10:00:07', '2024-04-03 08:01:36'),
(1175, 0, '', 'Gaur City Mall, Greater Noida West Road, Gaur City 1, Sector 4, Greater Noida, Ghaziabad, Uttar Pradesh, India', 10, 'Gaur City Mall', '28.6060285', '77.4296325', '10:00', '22:00', 0, '2023-10-18 10:44:52', '2024-04-03 08:01:36'),
(1180, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '18:03', '19:03', 0, '2023-10-18 12:33:46', '2024-04-03 08:01:36'),
(1181, 0, '', 'Amrapali Silicon City Gate no. 1, Amarpali Silicon City, Sector 76, Noida, Uttar Pradesh, India', 10, 'Work', '28.5677687', '77.3798237', '05:00', '23:00', 0, '2023-10-18 12:33:57', '2024-04-03 08:01:36'),
(1186, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '18:11', '19:11', 0, '2023-10-18 12:41:25', '2024-04-03 08:01:36'),
(1191, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 9, 'cts', '28.6222274', '77.3830285', '18:21', '19:21', 0, '2023-10-18 12:51:39', '2024-04-03 08:01:36'),
(1197, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 9, 'cts', '28.6222274', '77.3830285', '18:32', '19:32', 0, '2023-10-18 13:02:14', '2024-04-03 08:01:36'),
(1216, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 7, 'cts', '28.6222274', '77.3830285', '19:11', '20:11', 0, '2023-10-18 13:42:04', '2024-04-03 08:01:36'),
(1242, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 5, 'CTS', '28.6222274', '77.3830285', '12:00', '20:00', 0, '2023-10-19 12:47:54', '2024-04-03 08:01:36'),
(1778, 0, '', 'Tata Motors Limited, Sector 63 Road, D Block, Sector 63, Noida, Uttar Pradesh, India', 7, 'Tata', '28.6311401', '77.3824738', '11:00', '20:00', 0, '2023-10-23 09:08:03', '2024-04-03 08:01:36'),
(1779, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '14:46', '15:46', 0, '2023-10-23 09:15:59', '2024-04-03 08:01:36'),
(1811, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 9, 'cts', '28.6222274', '77.3830285', '15:40', '16:40', 0, '2023-10-23 10:10:17', '2024-04-03 08:01:36'),
(1824, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '15:52', '16:52', 0, '2023-10-23 10:22:25', '2024-04-03 08:01:36'),
(1835, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'CTS', '28.6222274', '77.3830285', '11:00', '21:00', 0, '2023-10-23 10:57:18', '2024-04-03 08:01:36'),
(1864, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'cts', '28.6222274', '77.3830285', '19:47', '20:47', 0, '2023-10-23 14:17:48', '2024-04-03 08:01:36'),
(1888, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'CTS', '28.6222274', '77.3830285', '11:00', '21:00', 0, '2023-10-23 14:41:53', '2024-04-03 08:01:36'),
(1901, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '20:15', '21:15', 0, '2023-10-23 14:45:36', '2024-04-03 08:01:36'),
(1915, 0, '', '2317 South 15th Avenue, Phoenix, AZ, USA', 1, 'Reds', '33.4263347', '-112.0907234', '05:00', '19:00', 0, '2023-10-24 02:21:34', '2024-04-03 08:01:36'),
(1916, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'CTS', '28.6222274', '77.3830285', '11:00', '21:00', 0, '2023-10-25 06:38:38', '2024-04-03 08:01:36'),
(1976, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'cts', '28.6222274', '77.3830285', '18:57', '19:57', 1, '2023-10-25 13:27:32', '2024-04-03 08:01:36'),
(1986, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 7, 'cts', '28.6222274', '77.3830285', '11:00', '21:28', 0, '2023-10-25 14:58:11', '2024-04-03 08:01:36'),
(1992, 0, '', '2317 South 15th Avenue, Phoenix, AZ, USA', 1, 'Reds', '33.4263347', '-112.0907234', '05:00', '19:00', 0, '2023-10-25 16:53:08', '2024-04-03 08:01:36'),
(2163, 0, '', 'HCL sector 126, Noida, Sector 126, Noida, Uttar Pradesh, India', 0.5, 'HCL', '28.5356469', '77.3437431', '11:00', '20:00', 0, '2023-10-26 12:28:59', '2024-04-03 08:01:36'),
(2218, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, '1234- CTS', '28.6222274', '77.3830285', '20:06', '21:05', 0, '2023-10-27 14:36:06', '2024-04-03 08:01:36'),
(2231, 0, '', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 9, 'cts', '28.6222274', '77.3830285', '12:10', '22:09', 0, '2023-10-31 06:39:45', '2023-10-31 06:39:45'),
(2672, 1, 'XYZ', 'Sector 62, Noida, Uttar Pradesh, India', 1, 'New Workplace', '28.627981', '77.3648567', '08:00', '20:00', 0, '2024-04-04 15:05:52', '2024-04-04 15:05:52'),
(2676, 1, 'Test', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'CTS', '28.6222274', '77.3830285', '09:00', '19:00', 0, '2024-04-12 11:24:19', '2024-04-12 11:24:19'),
(2677, 1, 'Test', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 7, 'CTS', '28.6222274', '77.3830285', '10:00', '20:00', 0, '2024-04-12 11:25:18', '2024-04-12 11:25:18'),
(2692, 1, 'Test', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'Noida', '28.6222274', '77.3830285', '18:00', '16:00', 0, '2024-04-19 07:04:56', '2024-04-19 07:04:56'),
(2693, 1, 'Test', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 6, 'Noida', '28.6222274', '77.3830285', '18:00', '16:00', 0, '2024-04-19 07:07:31', '2024-04-19 07:07:31'),
(2694, 1, 'Test', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 7, 'Noida', '28.6222274', '77.3830285', '06:00', '16:00', 0, '2024-04-19 07:10:07', '2024-04-19 07:10:07'),
(2695, 1, 'Test', 'HCL sector 126, Noida, Sector 126, Noida, Uttar Pradesh, India', 7, 'Noida', '28.5351213', '77.34438220000001', '06:00', '16:00', 0, '2024-04-19 07:10:33', '2024-04-19 07:10:33'),
(2697, 1, 'test', 'Chawtech Solutions Pvt. Ltd., D Block, Sector 63, Noida, Uttar Pradesh, India', 8, 'CTS', '28.6222274', '77.3830285', '11:11', '23:11', 0, '2024-04-19 10:19:06', '2024-04-19 10:19:06'),
(2698, 12, 'test', 'Muradnagar, Uttar Pradesh, India', 0.1, 'Test', '28.7718326', '77.5074593', '19:28', '07:28', 1, '2024-05-17 13:59:32', '2024-05-17 13:59:32'),
(2699, 12, 'test11', 'Muradnagar, Uttar Pradesh, India', 0.1, 'Test', '28.7718326', '77.5074593', '19:28', '07:28', 1, '2024-05-17 13:59:43', '2024-05-17 13:59:43'),
(2700, 1232, 'test11', 'Muradnagar, Uttar Pradesh, India', 0.1, 'Test', '28.7718326', '77.5074593', '19:28', '07:28', 1, '2024-05-17 13:59:52', '2024-05-17 13:59:52'),
(2701, 1232, 'test1166', 'Muradnagar, Uttar Pradesh, India', 0.1, 'Test', '28.7718326', '77.5074593', '19:28', '07:28', 1, '2024-05-17 14:00:02', '2024-05-17 14:00:02'),
(2702, 4554, '898989', 'Muradabad, Uttar Pradesh, India', 2, 'rgrg', '28.505583', '77.6922635', '11:30', '20:56', 1, '2024-05-17 14:20:58', '2024-05-17 14:20:58'),
(2703, 8, 'Test', 'Ghaziabad, Uttar Pradesh, India', 4, 'Noida', '28.6691565', '77.45375779999999', '11:30', '20:30', 0, '2024-05-17 14:30:17', '2024-05-17 14:30:17'),
(2704, 12, 'CTS TEST', 'Ghaziabad, Uttar Pradesh, India', 5, 'Noida', '28.6691565', '77.45375779999999', '11:30', '20:30', 0, '2024-05-17 14:31:17', '2024-05-17 14:31:17'),
(2705, 14, 'Red', 'Ghaziabad, Uttar Pradesh, India', 6, 'Ghaziabad', '28.6691565', '77.45375779999999', '11:30', '20:30', 1, '2024-05-17 14:32:14', '2024-05-17 14:32:14'),
(2706, 8978, 'test78', 'Muradnagar, Uttar Pradesh, India', 1, 'rgrg', '28.7718326', '77.5074593', '11:33', '20:33', 1, '2024-05-20 06:04:10', '2024-05-20 06:04:10'),
(2707, 8978, 'test78', 'Muradnagar, Uttar Pradesh, India', 1, 'rgrg', '28.7718326', '77.5074593', '11:33', '20:33', 1, '2024-05-20 06:06:25', '2024-05-20 06:06:25'),
(2708, 8978, 'test78', 'Muradnagar, Uttar Pradesh, India', 1, 'rgrg', '28.7718326', '77.5074593', '11:33', '20:33', 1, '2024-05-20 06:06:37', '2024-05-20 06:06:37'),
(2709, 8978, 'test78', 'Muradnagar, Uttar Pradesh, India', 1, 'rgrg', '28.7718326', '77.5074593', '11:33', '20:33', 1, '2024-05-20 06:06:53', '2024-05-20 06:06:53'),
(2717, 123445677, 'abbbxkkhkd', 'New Little Star Public School, Bhagwat Pura, Indra Nagar, Meerut, Uttar Pradesh, India', 3, 'Meerut', '28.9651941', '77.6937349', '06:20', '20:20', 0, '2024-05-20 06:59:06', '2024-05-20 06:59:06'),
(2718, 6, 'xxxxxxxx', 'New Little Star Public School, Bhagwat Pura, Indra Nagar, Meerut, Uttar Pradesh, India', 5, 'Meerut', '28.9651941', '77.6937349', '06:20', '08:20', 1, '2024-05-20 07:03:00', '2024-05-20 07:03:00'),
(2719, 23454, 'jpjoiho', 'New Little Star Public School, Bhagwat Pura, Indra Nagar, Meerut, Uttar Pradesh, India', 7, 'nimisha m ', '28.9651941', '77.6937349', '06:20', '14:20', 0, '2024-05-20 07:26:58', '2024-05-20 07:26:58'),
(2730, 5655, 'test70', 'Priyanka Beauty Parlour, Sarna Road, main market, Muradnagar, Uttar Pradesh, India', 0.06, 'SBS', '28.7804957', '77.4965242', '15:15', '23:55', 1, '2024-05-20 09:30:02', '2024-05-20 09:30:02'),
(2731, 111, 'Test Location', 'Suresh Nagar Colony, Thatipur, Gwalior, Madhya Pradesh, India', 8, 'Gwalior', '26.2188169', '78.2098735', '11:30', '20:30', 1, '2024-05-20 10:31:51', '2024-05-20 10:31:51'),
(2885, 5645, 'lklk', 'KIET GROUP OF INSTITUTIONS, Muradnagar, Uttar Pradesh, India', 0.97, 'gg', '28.7522471', '77.4987903', '09:15', '22:06', 0, '2024-05-20 14:36:33', '2024-05-20 14:36:33'),
(4352, 123, 'Tuji', 'Police chauki chungi no 3, Ravli Road, Malik Nagar, Muradnagar, Uttar Pradesh, India', 0.09, 'Test', '28.7843067', '77.49792099999999', '09:30', '21:33', 1, '2024-05-21 04:03:47', '2024-05-21 04:03:47'),
(5189, 22, 'Location', 'Suresh Nagar Colony, Thatipur, Gwalior, Madhya Pradesh, India', 5, 'Gwalior', '26.2188169', '78.2098735', '11:30', '20:30', 1, '2024-05-21 13:28:45', '2024-05-21 13:28:45'),
(5231, 555, 'test78', 'Priyanka Beauty Parlour, Sarna Road, main market, Muradnagar, Uttar Pradesh, India', 0.1, 'rgrg', '28.781080', '77.497036', '08:37', '20:37', 1, '2024-05-21 15:08:06', '2024-05-22 07:24:32'),
(5364, 434, 'test70', 'Madhya Pradesh, Khachna Naka, Hatta, Madhya Pradesh 470775, India', 0.1, '900', '24.135162', '79.598877', '11:45', '22:45', 0, '2024-05-22 07:15:47', '2024-05-22 07:19:41'),
(6845, 5, 'Location test', 'Suresh Nagar Colony, Thatipur, Gwalior, Madhya Pradesh, India', 0.11, 'Gwalior', '26.2188169', '78.2098735', '11:30', '20:30', 1, '2024-05-27 07:36:57', '2024-05-27 07:36:57'),
(6874, 6, 'Location test', 'New Jiwaji Nagar, Thatipur, Gwalior, Madhya Pradesh, India', 0.1, 'Gwalior', '26.220716', '78.2063696', '11:30', '17:30', 1, '2024-05-27 10:36:59', '2024-05-27 10:36:59'),
(6899, 56, 'test78', 'Priyanka Beauty Parlour, Sarna Road, main market, Muradnagar, Uttar Pradesh, India', 0.1, 'rgrg', '28.7804957', '77.4965242', '08:21', '23:21', 0, '2024-05-27 11:51:35', '2024-05-27 11:51:35'),
(7754, 77, 'tyu', 'New Jiwaji Nagar, Thatipur, Gwalior, Madhya Pradesh, India', 1, 'Gwalior', '26.220716', '78.2063696', '13:00', '04:00', 0, '2024-05-29 08:23:11', '2024-05-29 08:23:11');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(8591);

-- --------------------------------------------------------

--
-- Table structure for table `notification_details`
--

CREATE TABLE `notification_details` (
  `id` int(11) NOT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `is_read` bit(1) DEFAULT NULL,
  `notification` varchar(255) DEFAULT NULL,
  `response` varchar(255) DEFAULT NULL,
  `updated_date` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notification_details`
--

INSERT INTO `notification_details` (`id`, `created_date`, `is_read`, `notification`, `response`, `updated_date`, `user_id`) VALUES
(2670, '2024-03-28 05:18:30', b'0', 'Break time exceeded over 40 minutes', '{\"fcmKey\":\"cwEzF9UlRkqZmX8rhvm68q:APA91bHFQ3wVBA9ELVim02-rhm_C-HCAmO9QB9x9MAEPcwOCOsJO8u353wt7ch2DXD3GIPwUppzCq28lw_EM1U51nMuWV_H21JII9ll-Wb7i8AplNsJycJ1KjIZYk4LZAtI2mfulrhZu\"}', NULL, '5Kuju00NRgKZzqAWOPzf0QAAAAAAAAAAAAAAAAAAAAA'),
(2671, '2024-03-28 05:28:30', b'0', 'Break time exceeded over 50 minutes', '{\"fcmKey\":\"cwEzF9UlRkqZmX8rhvm68q:APA91bHFQ3wVBA9ELVim02-rhm_C-HCAmO9QB9x9MAEPcwOCOsJO8u353wt7ch2DXD3GIPwUppzCq28lw_EM1U51nMuWV_H21JII9ll-Wb7i8AplNsJycJ1KjIZYk4LZAtI2mfulrhZu\"}', NULL, '5Kuju00NRgKZzqAWOPzf0QAAAAAAAAAAAAAAAAAAAAA'),
(7249, '2024-05-28 11:57:43', b'0', 'Break time exceeded over 40 minutes', '{\"fcmKey\":\"c6B_7-0JT0a_BVGBjkrKgH:APA91bHZaX0-CT4usgKXxpb5YsN4TLXP26ACTF7Zdphh90_FdamJYejfVvO6NuomqxUBUcyTn3wFhpFLKnWW3S_M7GgzkvCXqmgPQcaki8I0IDxQThLkIMayjYN0no1YvYkngappBln2\"}', NULL, 'XWFCiij9SKyqORtdx76uEgAAAAAAAAAAAAAAAAAAAAA'),
(7250, '2024-05-28 12:07:43', b'0', 'Break time exceeded over 50 minutes', '{\"fcmKey\":\"c6B_7-0JT0a_BVGBjkrKgH:APA91bHZaX0-CT4usgKXxpb5YsN4TLXP26ACTF7Zdphh90_FdamJYejfVvO6NuomqxUBUcyTn3wFhpFLKnWW3S_M7GgzkvCXqmgPQcaki8I0IDxQThLkIMayjYN0no1YvYkngappBln2\"}', NULL, 'XWFCiij9SKyqORtdx76uEgAAAAAAAAAAAAAAAAAAAAA'),
(7798, '2024-05-29 02:50:43', b'0', 'Break time exceeded over 40 minutes', '{\"fcmKey\":\"ctzaTkh7R8ibSI82npbIUk:APA91bFqelVLOkmelOPbODlw6ToYLIB2f5PFZr79G9XNj4XBkD-khzXAtJUBO4pLChf3NVYhn-QtMGZmrhu_aj7YXKjyFaQpft0nCh6xWKC3sNAPkTgBMZybJnS_m66ax1lOkv3d8boq\"}', NULL, 'ZcbNTm7sTMO25EeZf0KKMwAAAAAAAAAAAAAAAAAAAAA'),
(7800, '2024-05-29 03:00:43', b'0', 'Break time exceeded over 50 minutes', '{\"fcmKey\":\"ctzaTkh7R8ibSI82npbIUk:APA91bFqelVLOkmelOPbODlw6ToYLIB2f5PFZr79G9XNj4XBkD-khzXAtJUBO4pLChf3NVYhn-QtMGZmrhu_aj7YXKjyFaQpft0nCh6xWKC3sNAPkTgBMZybJnS_m66ax1lOkv3d8boq\"}', NULL, 'ZcbNTm7sTMO25EeZf0KKMwAAAAAAAAAAAAAAAAAAAAA'),
(8287, '2024-05-30 09:38:43', b'0', 'Break time exceeded over 40 minutes', '{\"fcmKey\":\"dj8aUlflR1-UwRZn3Cb560:APA91bGo3vMgiI11D0mRO7PW-WDtON8o389Dw9pjQwK2PcEmvyRxURI9iribzOhN4JEej_ItmGIvdFPDj0yOtFoEo6CODBjqsUgJGM1Tsk9WMsixOLPzXU6opXvJLMxi5Dq2yZbRLYCE\"}', NULL, '5Kuju00NRgKZzqAWOPzf0QAAAAAAAAAAAAAAAAAAAAA'),
(8288, '2024-05-30 09:48:43', b'0', 'Break time exceeded over 50 minutes', '{\"fcmKey\":\"dj8aUlflR1-UwRZn3Cb560:APA91bGo3vMgiI11D0mRO7PW-WDtON8o389Dw9pjQwK2PcEmvyRxURI9iribzOhN4JEej_ItmGIvdFPDj0yOtFoEo6CODBjqsUgJGM1Tsk9WMsixOLPzXU6opXvJLMxi5Dq2yZbRLYCE\"}', NULL, '5Kuju00NRgKZzqAWOPzf0QAAAAAAAAAAAAAAAAAAAAA');

-- --------------------------------------------------------

--
-- Table structure for table `time_tracking`
--

CREATE TABLE `time_tracking` (
  `id` int(11) NOT NULL,
  `employee_id` varchar(128) NOT NULL,
  `job_id` int(11) NOT NULL,
  `job_name` varchar(128) NOT NULL,
  `clock_in` timestamp NOT NULL DEFAULT current_timestamp(),
  `clock_out` timestamp NULL DEFAULT NULL,
  `break_in` timestamp NULL DEFAULT NULL,
  `break_out` timestamp NULL DEFAULT NULL,
  `travel_in` text DEFAULT NULL,
  `travel_out` text DEFAULT NULL,
  `clock_in_location` int(11) DEFAULT NULL,
  `clock_out_location` int(11) DEFAULT NULL,
  `break_in_Location` int(11) DEFAULT NULL,
  `break_out_Location` int(11) DEFAULT NULL,
  `employee_state` varchar(128) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `notes` varchar(255) DEFAULT NULL,
  `reminder_40` bit(1) NOT NULL,
  `reminder_50` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `time_tracking`
--

INSERT INTO `time_tracking` (`id`, `employee_id`, `job_id`, `job_name`, `clock_in`, `clock_out`, `break_in`, `break_out`, `travel_in`, `travel_out`, `clock_in_location`, `clock_out_location`, `break_in_Location`, `break_out_Location`, `employee_state`, `created_date`, `updated_date`, `notes`, `reminder_40`, `reminder_50`) VALUES
(1432, 'J8FBHV', 3, 'Plasterer', '2023-10-19 20:02:56', '2023-10-19 21:50:10', '2023-10-19 20:03:12', '2023-10-19 21:50:10', NULL, NULL, 1242, 1, 1242, 1, 'checkedOut', '2023-10-19 14:32:56', '2023-10-19 14:32:56', NULL, b'0', b'0'),
(1477, '8QJ3OZ', 3, 'Plasterer', '2023-10-20 13:38:00', '2023-10-20 15:20:10', '2023-10-20 13:40:48', '2023-10-20 13:41:25', NULL, NULL, 1242, 1, 1242, 1242, 'checkedOut', '2023-10-20 08:08:00', '2023-10-20 08:08:00', NULL, b'0', b'0'),
(1540, 'J8FBHV', 3, 'Plasterer', '2023-10-20 15:47:05', '2023-10-20 18:33:10', '2023-10-20 17:02:54', '2023-10-20 18:33:10', NULL, NULL, 1242, 1, 1242, 1, 'checkedOut', '2023-10-20 10:17:05', '2023-10-20 10:17:05', NULL, b'0', b'0'),
(1557, 'XZCKXL', 3, 'Plasterer', '2023-10-20 16:07:38', '2023-10-20 16:41:10', NULL, NULL, NULL, NULL, 1242, 1, NULL, NULL, 'checkedOut', '2023-10-20 10:37:38', '2023-10-20 10:37:38', NULL, b'0', b'0'),
(1570, '918Y64', 1, 'Scaffold', '2023-10-20 16:16:05', '2023-10-20 16:48:10', NULL, NULL, NULL, NULL, 1242, 1, NULL, NULL, 'checkedOut', '2023-10-20 10:46:05', '2023-10-20 10:46:05', NULL, b'0', b'0'),
(1579, 'YE8A8C', 1, 'Scaffold', '2023-10-20 16:22:43', '2023-10-20 17:48:10', NULL, NULL, NULL, NULL, 1242, 1, NULL, NULL, 'checkedOut', '2023-10-20 10:52:43', '2023-10-20 10:52:43', NULL, b'0', b'0'),
(1595, 'E9Y85U', 3, 'Plasterer', '2023-10-20 16:39:13', '2023-10-20 17:10:10', NULL, NULL, NULL, NULL, 1242, 1, NULL, NULL, 'checkedOut', '2023-10-20 11:09:13', '2023-10-20 11:09:13', NULL, b'0', b'0'),
(1645, 'GKG1GR', 1, 'Scaffold', '2023-10-20 17:24:19', '2023-10-20 18:31:10', NULL, NULL, NULL, NULL, 1242, 1, NULL, NULL, 'checkedOut', '2023-10-20 11:54:19', '2023-10-20 11:54:19', NULL, b'0', b'0'),
(1666, 'NKAR53', 1, 'Scaffold', '2023-10-20 17:45:34', '2023-10-20 18:19:10', NULL, NULL, NULL, NULL, 1242, 1, NULL, NULL, 'checkedOut', '2023-10-20 12:15:34', '2023-10-20 12:15:34', NULL, b'0', b'0'),
(1682, 'ANH5JW', 1, 'Scaffold', '2023-10-20 18:06:05', '2023-10-20 18:06:46', '2023-10-20 18:06:38', '2023-10-20 18:06:46', NULL, NULL, 1242, 1242, 1242, 1242, 'checkedOut', '2023-10-20 12:36:05', '2023-10-20 12:36:05', NULL, b'0', b'0'),
(1694, 'PX89ID', 4, 'Travel', '2023-10-20 18:21:38', '2023-10-20 18:22:52', NULL, NULL, NULL, NULL, 1242, 1242, NULL, NULL, 'checkedOut', '2023-10-20 12:51:38', '2023-10-20 12:51:38', NULL, b'0', b'0'),
(1721, '7F7MKF', 1, 'Scaffold', '2023-10-20 19:26:10', '2023-10-20 20:12:10', NULL, NULL, NULL, NULL, 1242, 1, NULL, NULL, 'checkedOut', '2023-10-20 13:56:10', '2023-10-20 13:56:10', NULL, b'0', b'0'),
(1760, 'ANH5JW', 1, 'Scaffold', '2023-10-23 13:13:58', '2023-10-23 13:18:58', NULL, NULL, NULL, NULL, 1242, 1, NULL, NULL, 'checkedOut', '2023-10-23 07:43:58', '2023-10-23 07:43:58', NULL, b'0', b'0'),
(1763, 'XZCKXL', 2, 'Lather', '2023-10-23 13:15:25', '2023-10-23 13:20:26', NULL, NULL, NULL, NULL, 1242, 1, NULL, NULL, 'checkedOut', '2023-10-23 07:45:25', '2023-10-23 07:45:25', NULL, b'0', b'0'),
(1780, 'YE8A8C', 1, 'Scaffold', '2023-10-23 14:46:20', '2023-10-23 14:56:21', NULL, NULL, NULL, NULL, 1779, 1, NULL, NULL, 'checkedOut', '2023-10-23 09:16:20', '2023-10-23 09:16:20', NULL, b'0', b'0'),
(1783, 'E9Y85U', 3, 'Plasterer', '2023-10-23 14:50:11', '2023-10-23 15:58:18', '2023-10-23 14:50:44', '2023-10-23 15:58:18', NULL, NULL, 1778, 1, 1778, 1, 'checkedOut', '2023-10-23 09:20:11', '2023-10-23 09:20:11', NULL, b'0', b'0'),
(1812, 'GKG1GR', 1, 'Scaffold', '2023-10-23 15:40:22', '2023-10-23 15:45:23', NULL, NULL, NULL, NULL, 1811, 1, NULL, NULL, 'temporary_clockout', '2023-10-23 10:10:22', '2023-10-23 10:10:22', NULL, b'0', b'0'),
(1836, '8QJ3OZ', 4, 'Travel', '2023-10-23 15:27:34', '2023-10-23 16:49:10', NULL, NULL, NULL, NULL, 1835, 1, NULL, NULL, 'checkedOut', '2023-10-23 10:57:34', '2023-10-23 11:18:26', NULL, b'0', b'0'),
(1845, 'HE3UBQ', 1, 'Scaffold', '2023-10-23 17:26:43', '2023-10-23 19:08:20', NULL, NULL, NULL, NULL, 1835, 1, NULL, NULL, 'checkedOut', '2023-10-23 13:26:43', '2023-10-23 13:35:59', NULL, b'0', b'0'),
(1851, '3FST74', 1, 'Scaffold', '2023-10-23 19:42:03', '2023-10-23 19:43:33', '2023-10-23 19:42:24', '2023-10-23 19:42:51', NULL, NULL, 1835, 1835, 1835, 1835, 'checkedOut', '2023-10-23 14:12:03', '2023-10-23 14:12:03', NULL, b'0', b'0'),
(1865, 'DOU3MW', 4, 'Travel', '2023-10-23 19:47:54', '2023-10-23 19:55:48', '2023-10-23 19:49:41', '2023-10-23 19:50:14', NULL, NULL, 1835, 1, 1835, 1835, 'checkedOut', '2023-10-23 14:17:54', '2023-10-23 14:17:54', NULL, b'0', b'0'),
(1889, 'V3OU3G', 1, 'Scaffold', '2023-10-23 20:14:13', '2023-10-23 20:15:16', '2023-10-23 20:14:31', '2023-10-23 20:14:37', NULL, NULL, 1888, 1888, 1888, 1888, 'checkedOut', '2023-10-23 14:44:13', '2023-10-23 14:44:13', NULL, b'0', b'0'),
(1902, '7RW418', 3, 'Plasterer', '2023-10-23 20:15:41', '2023-10-23 20:28:15', '2023-10-23 20:16:31', '2023-10-23 20:16:40', NULL, NULL, 1888, 1, 1888, 1888, 'checkedOut', '2023-10-23 14:45:41', '2023-10-23 14:45:41', NULL, b'0', b'0'),
(1943, 'DOU3MW', 2, 'Lather', '2023-10-25 17:26:59', '2023-10-25 18:33:20', NULL, NULL, NULL, NULL, 1916, 1, NULL, NULL, 'checkedOut', '2023-10-25 11:56:59', '2023-10-25 11:56:59', NULL, b'0', b'0'),
(1977, 'YE8A8C', 4, 'Travel', '2023-10-25 18:57:41', '2023-10-25 19:22:43', NULL, NULL, NULL, NULL, 1976, 1, NULL, NULL, 'checkedOut', '2023-10-25 13:27:41', '2023-10-25 13:27:41', NULL, b'0', b'0'),
(1987, '3FST74', 4, 'Travel', '2023-10-25 20:28:14', '2023-10-25 21:03:20', NULL, NULL, NULL, NULL, 1986, 1, NULL, NULL, 'checkedOut', '2023-10-25 14:58:14', '2023-10-25 14:58:14', NULL, b'0', b'0'),
(2002, 'ANH5JW', 2, 'Lather', '2023-10-26 13:49:52', '2023-10-26 16:47:20', NULL, NULL, NULL, NULL, 1986, 1, NULL, NULL, 'checkedOut', '2023-10-26 08:19:52', '2023-10-26 08:19:52', NULL, b'0', b'0'),
(2154, '3FST74', 4, 'Travel', '2023-10-26 17:00:32', '2023-10-26 18:31:46', NULL, NULL, NULL, NULL, 1986, 1, NULL, NULL, 'checkedOut', '2023-10-26 11:30:32', '2023-10-26 11:30:32', NULL, b'0', b'0'),
(2160, 'PX89ID', 4, 'Travel', '2023-10-26 17:58:24', '2023-10-26 18:28:46', NULL, NULL, NULL, NULL, 1986, 1, NULL, NULL, 'checkedOut', '2023-10-26 12:28:24', '2023-10-26 12:28:24', NULL, b'0', b'0'),
(2166, 'DOU3MW', 4, 'Travel', '2023-10-26 19:03:29', '2023-10-26 21:00:46', NULL, NULL, NULL, NULL, 1986, 1, NULL, NULL, 'checkedOut', '2023-10-26 13:33:29', '2023-10-26 13:33:29', NULL, b'0', b'0'),
(2193, 'PX89ID', 2, 'Lather', '2023-10-27 14:48:53', '2023-10-27 15:19:46', NULL, NULL, NULL, NULL, 1986, 1, NULL, NULL, 'checkedOut', '2023-10-27 09:18:53', '2023-10-27 09:18:53', NULL, b'0', b'0'),
(2196, 'YE8A8C', 2, 'Lather', '2023-10-27 16:13:43', '2023-10-27 16:49:46', NULL, NULL, NULL, NULL, 1986, 1, NULL, NULL, 'checkedOut', '2023-10-27 10:43:43', '2023-10-27 10:43:43', NULL, b'0', b'0'),
(2202, 'J3E8H7', 4, 'Travel', '2023-10-27 17:26:35', '2023-10-27 18:08:46', NULL, NULL, '2023-10-27 18:15:38', NULL, 1986, 1, NULL, NULL, 'travelIn', '2023-10-27 11:56:35', '2024-01-18 10:57:33', NULL, b'0', b'0'),
(2210, '7RW418', 4, 'Travel', '2023-10-27 18:46:31', '2023-10-27 19:16:46', NULL, NULL, NULL, NULL, 1986, 1, NULL, NULL, 'checkedOut', '2023-10-27 13:16:31', '2023-10-27 13:16:31', NULL, b'0', b'0'),
(2215, '918Y64', 4, 'Travel', '2023-10-27 19:22:54', '2023-10-27 19:53:46', NULL, NULL, NULL, NULL, 1986, 1, NULL, NULL, 'checkedOut', '2023-10-27 13:52:54', '2023-10-27 13:52:54', NULL, b'0', b'0'),
(2219, 'XZCKXL', 2, 'Lather', '2023-10-27 20:06:25', '2023-10-27 20:15:07', '2023-10-27 20:09:21', '2023-10-27 20:12:52', NULL, NULL, 2218, 2218, 2218, 2218, 'checkedOut', '2023-10-27 14:36:25', '2023-10-27 14:36:25', NULL, b'0', b'0'),
(2232, 'XZCKXL', 2, 'Lather', '2023-10-31 12:10:49', '2023-10-31 12:41:46', NULL, NULL, NULL, NULL, 2231, 1, NULL, NULL, 'checkedOut', '2023-10-31 06:40:49', '2023-10-31 06:40:49', NULL, b'0', b'0'),
(2237, '7RW418', 4, 'Travel', '2023-11-21 14:19:46', '2023-11-21 14:29:12', NULL, NULL, NULL, NULL, 2231, 2231, NULL, NULL, 'checkedOut', '2023-11-21 08:49:46', '2023-11-21 08:49:46', NULL, b'0', b'0'),
(2243, 'HE3UBQ', 1, 'Scaffold', '2023-12-18 19:02:43', '2023-12-18 19:32:56', NULL, NULL, NULL, NULL, 252, 1, NULL, NULL, 'checkedOut', '2023-12-18 13:32:43', '2023-12-18 13:32:43', NULL, b'0', b'0'),
(2358, 'DOU3MW', 1, 'Scaffold', '2023-12-21 16:08:03', '2023-12-21 16:09:18', '2023-12-21 16:08:21', '2023-12-21 16:08:31', NULL, NULL, 2231, 2231, 2231, 2231, 'checkedOut', '2023-12-21 10:38:03', '2023-12-21 10:38:03', NULL, b'0', b'0'),
(2732, 'ANH5JW', 1, 'Scaffold', '2024-05-20 11:24:26', '2024-05-20 12:14:57', NULL, NULL, NULL, NULL, 2730, 1, NULL, NULL, 'checkedOut', '2024-05-20 11:24:26', '2024-05-20 11:24:26', NULL, b'0', b'0'),
(2736, '3FST74', 1, 'Scaffold', '2024-05-20 11:33:45', '2024-05-20 14:30:57', NULL, NULL, NULL, NULL, 2731, 1, NULL, NULL, 'checkedOut', '2024-05-20 11:33:45', '2024-05-20 11:33:45', NULL, b'0', b'0'),
(4622, '3FST74', 1, 'Scaffold', '2024-05-21 08:37:23', '2024-05-21 09:12:57', '2024-05-21 08:37:38', '2024-05-21 08:40:08', NULL, NULL, 2731, 1, 2731, 2731, 'checkedOut', '2024-05-21 08:37:23', '2024-05-21 08:37:23', NULL, b'0', b'0'),
(5382, '4F7L5F', 2, 'Lather', '2024-05-22 08:35:01', '2024-05-22 08:55:08', NULL, NULL, NULL, NULL, 5364, 1, NULL, NULL, 'checkedOut', '2024-05-22 08:35:01', '2024-05-22 08:35:01', NULL, b'0', b'0'),
(6848, '3FST74', 1, 'Scaffold', '2024-05-27 07:38:39', '2024-05-27 08:10:43', '2024-05-27 07:39:50', '2024-05-27 07:39:59', NULL, NULL, 5189, 1, 5189, 5189, 'checkedOut', '2024-05-27 07:38:39', '2024-05-27 07:38:39', NULL, b'0', b'0'),
(7375, '3FST74', 1, 'Scaffold', '2024-05-28 09:20:36', '2024-05-28 10:33:43', '2024-05-28 09:20:51', '2024-05-28 09:54:00', NULL, NULL, 5189, 1, 5189, 5189, 'checkedOut', '2024-05-28 09:20:36', '2024-05-28 09:20:36', NULL, b'0', b'0'),
(7755, 'J8FBHV', 3, 'Plasterer', '2024-05-29 08:23:57', '2024-05-29 10:20:43', '2024-05-29 08:40:02', '2024-05-29 09:31:49', NULL, NULL, 7754, 1, 7754, 7754, 'travelOut', '2024-05-29 08:23:57', '2024-05-29 08:23:57', NULL, b'1', b'1'),
(7842, 'ANH5JW', 3, 'Plasterer', '2024-05-29 10:05:14', '2024-05-29 12:55:43', '2024-05-29 10:07:59', '2024-05-29 10:08:27', NULL, NULL, 7754, 1, 7754, 7754, 'checkedOut', '2024-05-29 10:05:14', '2024-05-29 10:05:14', NULL, b'0', b'0'),
(7882, 'DOU3MW', 2, 'Lather', '2024-05-29 13:10:57', '2024-05-29 14:36:43', '2024-05-29 13:38:30', '2024-05-29 14:06:08', NULL, NULL, 7754, 1, 7754, 7754, 'checkedOut', '2024-05-29 13:10:57', '2024-05-29 13:10:57', NULL, b'0', b'0'),
(8265, 'DOU3MW', 2, 'Lather', '2024-05-30 12:28:36', '2024-05-30 12:59:43', NULL, NULL, NULL, NULL, 7754, 1, NULL, NULL, 'checkedOut', '2024-05-30 12:28:36', '2024-05-30 12:28:36', NULL, b'0', b'0'),
(8461, '3FST74', 4, 'Travel', '2024-05-31 15:17:20', '2024-05-31 15:25:29', NULL, NULL, NULL, NULL, 7754, 1, NULL, NULL, 'checkedOut', '2024-05-31 15:17:20', '2024-05-31 15:17:20', NULL, b'0', b'0'),
(8466, 'ANH5JW', 1, 'Scaffold', '2024-06-03 13:39:52', '2024-06-03 18:29:59', '2024-06-03 13:40:14', '2024-06-03 13:44:41', NULL, NULL, 7754, 7754, 7754, 7754, 'checkedOut', '2024-06-03 13:39:52', '2024-06-03 13:39:52', NULL, b'0', b'0'),
(8468, '3FST74', 3, 'Plasterer', '2024-06-03 14:04:09', '2024-06-03 18:29:59', '2024-06-03 14:04:29', '2024-06-03 15:04:57', NULL, NULL, 7754, 7754, 7754, 7754, 'checkedOut', '2024-06-03 14:04:09', '2024-06-03 14:04:09', NULL, b'0', b'0'),
(8486, 'MEEXCW', 1, 'Scaffold', '2024-06-04 13:42:14', '2024-06-04 15:12:42', '2024-06-04 13:43:29', '2024-06-04 13:43:57', NULL, NULL, 7754, 1, 7754, 7754, 'checkedOut', '2024-06-04 13:42:14', '2024-06-04 13:42:14', NULL, b'0', b'0'),
(8490, 'GR7HQA', 4, 'Travel', '2024-06-04 14:39:04', '2024-06-04 18:29:59', NULL, NULL, NULL, NULL, 7754, 7754, NULL, NULL, 'checkedOut', '2024-06-04 14:39:04', '2024-06-04 14:39:04', NULL, b'0', b'0'),
(8505, 'GR7HQA', 2, 'Lather', '2024-06-06 11:16:25', '2024-06-06 18:29:59', '2024-06-06 12:34:02', '2024-06-06 13:36:06', NULL, NULL, 7754, 7754, 7754, 7754, 'checkedOut', '2024-06-06 11:16:25', '2024-06-06 11:16:25', NULL, b'0', b'0'),
(8540, '7RW418', 2, 'Lather', '2024-06-10 15:13:07', '2024-06-10 15:47:31', NULL, NULL, NULL, NULL, 7754, 1, NULL, NULL, 'temporary_clockout', '2024-06-10 15:13:07', '2024-06-10 15:13:07', NULL, b'0', b'0'),
(8552, 'FCPODE', 1, 'Scaffold', '2024-06-11 08:41:09', '2024-06-11 09:49:43', '2024-06-11 08:43:58', '2024-06-11 09:49:43', NULL, NULL, 7754, 1, 7754, 1, 'checkedOut', '2024-06-11 08:41:09', '2024-06-11 08:41:09', NULL, b'0', b'0'),
(8553, 'MEEXCW', 2, 'Lather', '2024-06-11 11:46:56', '2024-06-11 18:29:59', NULL, NULL, NULL, NULL, 7754, 7754, NULL, NULL, 'checkedOut', '2024-06-11 11:46:56', '2024-06-11 11:46:56', NULL, b'0', b'0'),
(8555, 'X3AUMY', 2, 'Lather', '2024-06-11 17:08:36', '2024-06-11 18:29:59', '2024-06-11 17:12:45', '2024-06-11 17:12:54', NULL, NULL, 7754, 7754, 7754, 7754, 'checkedOut', '2024-06-11 17:08:36', '2024-06-11 17:08:36', NULL, b'0', b'0'),
(8556, 'PX89ID', 2, 'Lather', '2024-06-11 17:18:01', '2024-06-11 17:18:08', NULL, NULL, NULL, NULL, 7754, 1, NULL, NULL, 'checkedOut', '2024-06-11 17:18:01', '2024-06-11 17:18:01', NULL, b'0', b'0'),
(8566, 'QQI3IO', 1, 'Scaffold', '2024-06-12 10:04:49', '2024-06-12 11:51:57', NULL, NULL, NULL, NULL, 7754, 1, NULL, NULL, 'checkedOut', '2024-06-12 10:04:49', '2024-06-12 10:04:49', NULL, b'0', b'0'),
(8572, 'XZCKXL', 2, 'Lather', '2024-06-13 07:03:28', '2024-06-13 18:29:59', '2024-06-13 07:04:39', '2024-06-13 08:08:48', NULL, NULL, 6899, 6899, 6899, 6899, 'checkedOut', '2024-06-13 07:03:28', '2024-06-13 07:03:28', NULL, b'0', b'0'),
(8577, 'FLOXWY', 1, 'Scaffold', '2024-06-18 13:04:52', '2024-06-18 18:29:59', '2024-06-18 13:05:16', '2024-06-18 13:05:40', NULL, NULL, 7754, 7754, 7754, 7754, 'checkedOut', '2024-06-18 13:04:52', '2024-06-18 13:04:52', NULL, b'0', b'0'),
(8578, '656ATY', 1, 'Scaffold', '2024-06-18 12:26:44', '2024-06-18 18:29:59', '2024-06-18 12:27:14', '2024-06-18 13:37:43', NULL, NULL, 2717, 2717, 2717, 1, 'checkedOut', '2024-06-18 13:26:44', '2024-06-18 14:50:19', NULL, b'0', b'0'),
(8579, 'V3OU3G', 3, 'Plasterer', '2024-06-18 14:22:15', '2024-06-18 15:27:43', '2024-06-18 14:22:43', '2024-06-18 15:27:43', NULL, NULL, 7754, 1, 7754, 1, 'checkedOut', '2024-06-18 14:22:15', '2024-06-18 14:22:15', NULL, b'0', b'0'),
(8580, '656ATY', 2, 'Lather', '2024-06-19 05:52:29', '2024-06-19 18:29:59', '2024-06-19 05:53:02', '2024-06-19 05:53:25', NULL, NULL, 2717, 2717, 2717, 2717, 'checkedOut', '2024-06-19 05:52:29', '2024-06-19 05:52:29', NULL, b'0', b'0'),
(8581, 'V3OU3G', 1, 'Scaffold', '2024-06-19 11:04:07', '2024-06-19 14:18:01', '2024-06-19 11:04:26', '2024-06-19 12:07:46', NULL, NULL, 7754, 1, 7754, 7754, 'checkedOut', '2024-06-19 11:04:08', '2024-06-19 11:04:08', NULL, b'0', b'0'),
(8590, 'BQITOJ', 1, 'Scaffold', '2024-06-20 06:50:56', '2024-06-20 06:51:13', NULL, NULL, NULL, NULL, 6899, 1, NULL, NULL, 'checkedOut', '2024-06-20 06:50:56', '2024-06-20 06:50:56', NULL, b'0', b'0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_details`
--
ALTER TABLE `admin_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `emp_location`
--
ALTER TABLE `emp_location`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notification_details`
--
ALTER TABLE `notification_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `time_tracking`
--
ALTER TABLE `time_tracking`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_details`
--
ALTER TABLE `admin_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `emp_location`
--
ALTER TABLE `emp_location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7755;

--
-- AUTO_INCREMENT for table `time_tracking`
--
ALTER TABLE `time_tracking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8591;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
