# Desktop Application - Batik Sales

Batik Sales is a desktop application designed to simplify the process of selling and purchasing batik stock. This application is equipped with features to manage batik sales and stock purchases, as well as providing master data that includes information on employees, customers, products, and suppliers. Additionally, users can efficiently access sales and stock purchase data, and generate reports that help analyze the overall performance of the batik business. This application using Java as programming language and Oracle database.

## Tech Stack

- **Java** - The main programming language for this application.
- **Oracle Database** - The database used to store and retrieve data.
- **PL/SQL** - Used to create functions, procedures, and triggers.
- **JasperViewer** - Used to generate reports.

## Features

- Main features available in this application:
  - Data management using Oracle Database and PL/SQL.
  - Management of master data, including employees, customers, products, and suppliers.
  - Management of sales and purchases of stocks.
  - Generation of various reports.

## Installation

Follow the steps below to clone and run the project in your local environment:

1. Clone repository:

    ```bash
    git clone https://github.com/Akbarwp/UAS-PBD.git
    ```

2. Open project in NetBeans IDE or etc.

3. Make sure to have at least **JDK version 8** installed on your system.

4. Add the JDBC driver for Oracle to the project:
    - Download the JDBC driver from [Oracle JDBC Driver](https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html).
    - Add the `.jar` file to your project’s library.

5. Configure the connection to the Oracle Database:
    - Add the connection configuration in the `Koneksi.java` file.

6. Run the application using your IDE in the `MenuUtama.java` file.

## Database Setup

1. **Create Database dan Table:**
   - Database Scheme

<img src="https://github.com/user-attachments/assets/b6977f83-f8c3-46df-9db5-4720cb4e0603" alt="Diagram Scheme" width="700" />
<br><br>

   - Documentation PL/SQL, for create functions, procedures, and triggers.

[Dokumentasi PL/SQL.pdf](https://github.com/user-attachments/files/17089344/Dokumentasi.PBD.Batik.-.PL.SQL.pdf)

## Screenshots

- ### **Homepage**

<img src="https://github.com/user-attachments/assets/06407140-fff6-4f7a-b4bb-3292c13c26ac" alt="Halaman Utama" width="" />
<br><br>

- ### **Employees page**

<img src="https://github.com/user-attachments/assets/52b8164b-bae1-468f-912d-2518dd8fd9bb" alt="Halaman Master Pegawai" width="" />
&nbsp;&nbsp;&nbsp;
<img src="https://github.com/user-attachments/assets/ae408c9c-5908-483c-b267-326a5cca2cbc" alt="Halaman Tabel Pegawai" width="" />
&nbsp;&nbsp;&nbsp;
<img src="https://github.com/user-attachments/assets/ce2cb718-1279-4cd0-a5d2-c508fb3e334d" alt="Halaman Tambah Pegawai" width="" />
&nbsp;&nbsp;&nbsp;
<br><br>

- ### **Sales page**

<img src="https://github.com/user-attachments/assets/8b335b7d-5337-4b8c-a44f-e8151b0b646e" alt="Halaman Master Penjualan" width="" />
&nbsp;&nbsp;&nbsp;
<img src="https://github.com/user-attachments/assets/e4e461e5-7f53-4d7c-b07d-f3fc6ca80954" alt="Halaman Tabel Penjualan" width="" />
&nbsp;&nbsp;&nbsp;
<img src="https://github.com/user-attachments/assets/c77e549a-7c77-4f4d-b3b3-a88bf253d2eb" alt="Halaman Tabel Detail Penjualan" width="" />
&nbsp;&nbsp;&nbsp;
<img src="https://github.com/user-attachments/assets/147d737b-2bbc-4c28-be4f-952891ba8de6" alt="Halaman Tambah Penjualan" width="" />
&nbsp;&nbsp;&nbsp;
<br><br>

- ### **Reports page**

<img src="https://github.com/user-attachments/assets/2796a96b-45cd-4b20-81ff-04bb42f57b5b" alt="Halaman Laporan" width="" />
&nbsp;&nbsp;&nbsp;
<img src="https://github.com/user-attachments/assets/1e94bf3d-205f-4ed3-9954-69f81ecd30b8" alt="Laporan Penjualan" width="" />
