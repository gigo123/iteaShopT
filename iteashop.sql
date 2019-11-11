-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Час створення: Лис 11 2019 р., 12:05
-- Версія сервера: 10.3.13-MariaDB
-- Версія PHP: 7.1.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База даних: `iteashop`
--

-- --------------------------------------------------------

--
-- Структура таблиці `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `price` int(11) NOT NULL,
  `category` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп даних таблиці `products`
--

INSERT INTO `products` (`id`, `name`, `description`, `price`, `category`) VALUES
(1, 'iPhone 11 Pro', 'All-new triple-camera system (Ultra Wide, Wide, Telephoto)\nUp to 20 hours of video playback1\nWater resistant to a depth of 4 meters for up to 30 minutes2\n5.8” or 6.5” Super Retina XDR display3\n ', 999, 1),
(2, 'iPhone 11', 'All-new dual-camera system (Ultra Wide, Wide)\nUp to 17 hours of video playback1\nWater resistant to a depth of 2 meters for up to 30 minutes2\n6.1” Liquid Retina HD display3\n', 699, 1),
(3, 'iPhone Xr', 'Single-camera system \n(Wide)\nUp to 16 hours of video playback1\nWater resistant to a depth of 1 meter for up to 30 minutes2\n6.1” Liquid Retina HD display3\n', 599, 1),
(4, 'iPhone 8', '	Single (Wide) or dual-camera system (Wide, Telephoto)\n•	Up to 14 hours of video playback1\n•	Water resistant to a depth of 1 meter for up to 30 minutes2\n•	4.7” or 5.5” Retina HD display', 499, 1),
(5, 'iPad Pro', '12.9” and 11”\nLiquid Retina displayA12X Bionic chip\nFace ID\nUp to 1TB storage\nSupport for Apple Pencil\n(2nd generation)\n•	Support for Smart Keyboard Folio', 799, 2),
(6, 'iPad Air', '•	10.5″ Retina display\n•	A12 Bionic chip\n•	Touch ID\n•	Up to 256GB storage\n•	Support for Apple Pencil\n(1st generation)\n•	Support for Smart Keyboard', 499, 2),
(7, 'iPad', '•	10.2″ Retina display\n•	A10 Fusion chip\n•	Touch ID\n•	Up to 128GB storage\n•	Support for Apple Pencil\n(1st generation)\n•	Support for Smart Keyboard', 329, 2),
(8, 'iPad mini', '•	7.9″ Retina display A12 Bionic chip\n Touch ID\nUp to 256GB storage\nSupport for Apple Pencil (1st generation)', 499, 2),
(9, 'Apple Watch Nike', 'Apple Watch Nike is the perfect running partner with the Nike Run Club app. Featuring a world of workouts, coaching, and motivation to help take your fitness to the next level. ', 499, 3),
(10, 'Apple Watch Hermès', 'A partnership based on parallel thinking and shared values continues. Now with a wide range of new and current Hermès cases and bands, new faces designed for the Always-On Retina display, and a special combination in all black. ', 1399, 3),
(11, 'Apple Watch Series 5', 'There’s an Apple Watch for everyone', 399, 3),
(12, 'Apple Watch Series 3', 'Stay active. Stay healthy. Stay connected.', 299, 3);

-- --------------------------------------------------------

--
-- Структура таблиці `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  `region` varchar(50) NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `comment` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп даних таблиці `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `name`, `region`, `gender`, `comment`) VALUES
(5, 'some@email.com', '098030ef05d215eccf92ecc77a9d284c', 'new name', 'DNR', 1, '						some comment 3\r\n							');

--
-- Індекси збережених таблиць
--

--
-- Індекси таблиці `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Індекси таблиці `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для збережених таблиць
--

--
-- AUTO_INCREMENT для таблиці `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT для таблиці `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
