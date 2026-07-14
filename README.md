
<p align="center">
  <img src="src/Source Image/Untitled-2.png" alt="Mega Mart Z Logo" width="200"/>
</p>

<h1 align="center">🏢 Mega Mart Z — Company Management System</h1>

<p align="center">
  <strong>A comprehensive enterprise-grade desktop application for managing all aspects of company operations</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Swing-UI_Framework-blue?style=for-the-badge&logo=java&logoColor=white" alt="Swing"/>
  <img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
  <img src="https://img.shields.io/badge/FlatLaf-Modern_Theme-green?style=for-the-badge" alt="FlatLaf"/>
  <img src="https://img.shields.io/badge/JasperReports-Reporting-red?style=for-the-badge" alt="JasperReports"/>
  <img src="https://img.shields.io/badge/NetBeans-IDE-1B6AC6?style=for-the-badge&logo=apachenetbeans&logoColor=white" alt="NetBeans"/>
</p>

---

## 📋 Table of Contents

- [About The Project](#-about-the-project)
- [Screenshots](#-screenshots)
- [Key Features](#-key-features)
- [System Architecture](#-system-architecture)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Database Setup](#-database-setup)
- [Installation & Setup](#-installation--setup)
- [Usage Guide](#-usage-guide)
- [Module Details](#-module-details)
- [Libraries & Dependencies](#-libraries--dependencies)
- [Contributors](#-contributors)
- [License](#-license)

---

## 📖 About The Project

**Mega Mart Z — Company Management System (CMS)** is a full-featured Java Swing desktop application designed for **Mega Mart Z**, a retail/wholesale company. The system provides a unified platform to manage all critical business operations, including **Human Resources**, **Accounting & Finance**, **Inventory & Stock**, **Customer Relationships**, **Marketing**, **Internal & External Communications**, and **Reporting & Analytics**.

Built as a group project, this application features a modern, clean UI powered by **FlatLaf**, role-based access control, dynamic data visualization with **JFreeChart**, comprehensive PDF report generation via **JasperReports**, and email integration using **JavaMail API**.

### ✨ Highlights

- 🎨 **Modern UI/UX** — Sleek, professional interface with FlatLaf Mac Light theme
- 🔐 **Role-Based Access Control** — Department-based permissions for secure data access
- 📊 **Real-Time Analytics** — Interactive bar charts, line charts, and polar area charts
- 📄 **PDF Report Generation** — 30+ JasperReports templates for comprehensive reporting
- 📧 **Email Integration** — Send and receive emails directly within the application
- 🖨️ **Invoice & GRN Management** — Full inventory lifecycle with printable documents
- 📝 **Logging** — Java Logging API for tracking system activities (`CMS.log`)

---

## 📸 Screenshots

### Employee Registration
> Full employee registration form with department assignment, job roles, and personal details.

![Employee Registration](src/images/Screenshot%202025-05-23%20132319.png)

---

### Leave Management
> Track and manage employee leave requests with leave type categorization and search filters.

![Leave Management](src/images/Screenshot%202025-05-23%20132408.png)

---

### Training Management
> Assign and monitor employee training programs across departments.

![Training Management](src/images/Screenshot%202025-05-23%20132431.png)

---

### Attendance Marking
> Mark daily attendance with search functionality, employee detail preview, and sort options.

![Attendance Marking](src/images/Screenshot%202025-05-23%20132454.png)

---

### Payroll Management
> Complete payroll processing with salary calculation, NI contributions, pension, overtime, deductions, and loan management.

![Payroll Management](src/images/Screenshot%202025-05-23%20132517.png)

---

## 🚀 Key Features

### 🏠 Dashboard
| Feature | Description |
|---------|-------------|
| Income Overview | Monthly income bar chart with year-to-date data |
| GRN Analytics | Line chart visualization for Goods Received Notes |
| Data Summaries | Real-time data boxes with key metrics |
| Polar Area Chart | Geo-chart visualization for business intelligence |

### 👥 Human Resource Department
| Feature | Description |
|---------|-------------|
| Employee Registration | Complete employee onboarding with ID, name, contact, department, job role, username/password, and address |
| Leave Management | Track employee leaves (Earned, Maternity, Sick, Unpaid, Marriage, Paternity) with search & filter |
| Training Management | Assign training programs and track employee training records |
| Attendance Marking | Daily attendance with Present/Absent marking, employee search, and sorting |
| Payroll Management | Full salary processing — Basic, Overtime, Pension, NI, Loans, Deductions, and Net Pay calculation |
| Personal Details | View and update employee personal information |

### 💰 Accounting Department
| Feature | Description |
|---------|-------------|
| Account Management | Manage company financial accounts |
| Transaction Management | Record, track, and manage all financial transactions |
| Budget Management | Department-wise budget allocation and tracking |
| Loan Management | Employee and company loan tracking with repayment schedules |

### 🛒 Customer Relationship
| Feature | Description |
|---------|-------------|
| Customer Registration | Register new customers with complete profile management |
| Customer Feedback | Collect and manage customer feedback |
| Customer Complaints | Track and resolve customer complaints |

### 📈 Marketing Department
| Feature | Description |
|---------|-------------|
| Digital Marketing | Manage and track digital marketing campaigns |
| Market Research & Analysis | Conduct market research with analytical insights |
| Advertising & Promotions | Plan and manage promotional campaigns |
| Social Media Management | Monitor and manage social media presence |

### 📦 Inventory Management
| Feature | Description |
|---------|-------------|
| Stock Management | Track current stock levels with product & brand categorization |
| Property Management | Manage company property and assets |
| Goods Received Note (GRN) | Record incoming goods with supplier details and pricing |
| Invoice Generation | Create, print, and export customer invoices as PDF |

### 📧 Communication Tools
| Feature | Description |
|---------|-------------|
| External Communication | Send emails to external contacts using JavaMail API |
| Internal Communication | Inter-departmental messaging system |
| Mail Box | View received emails and manage correspondence |

### 📊 Reporting & Analytics
| Feature | Description |
|---------|-------------|
| Comprehensive Reports | 30+ JasperReports templates covering all departments |
| Attendance Reports | Generate employee attendance reports |
| Events Management | Track and report on company events |

---

## 🏗️ System Architecture

```
┌──────────────────────────────────────────────────────────────────┐
│                        PRESENTATION LAYER                        │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌─────────────────┐  │
│  │  Login    │  │  Home    │  │  Frames  │  │  Custom UI      │  │
│  │  Frame    │  │  Frame   │  │  (HRM,   │  │  Components     │  │
│  │          │  │          │  │  Inv.)   │  │  (FlatLaf)      │  │
│  └──────────┘  └──────────┘  └──────────┘  └─────────────────┘  │
├──────────────────────────────────────────────────────────────────┤
│                        BUSINESS LOGIC LAYER                      │
│  ┌───────────────┐  ┌──────────────┐  ┌───────────────────────┐  │
│  │  Panel Classes │  │  Model       │  │  Chart & Reporting   │  │
│  │  (Dashboard,  │  │  Classes     │  │  (JFreeChart,        │  │
│  │   HRM, Acct,  │  │  (User,      │  │   JasperReports)     │  │
│  │   Inventory)  │  │   Menu, etc) │  │                       │  │
│  └───────────────┘  └──────────────┘  └───────────────────────┘  │
├──────────────────────────────────────────────────────────────────┤
│                        DATA ACCESS LAYER                         │
│  ┌──────────────────────────────────────────────────────────────┐ │
│  │                    MySQL.java                                │ │
│  │  • Connection Management (JDBC)                              │ │
│  │  • PreparedStatement for INSERT/UPDATE/DELETE (executeIUD)   │ │
│  │  • PreparedStatement for SELECT (executeSearch)              │ │
│  └──────────────────────────────────────────────────────────────┘ │
├──────────────────────────────────────────────────────────────────┤
│                        DATABASE LAYER                            │
│  ┌──────────────────────────────────────────────────────────────┐ │
│  │                MySQL Server (c.m.system)                     │ │
│  │  Tables: employee, attendance, leave, training, payroll,     │ │
│  │          accounts, transactions, budgets, loans, customers,  │ │
│  │          invoices, grn, stock, property, marketing, etc.     │ │
│  └──────────────────────────────────────────────────────────────┘ │
└──────────────────────────────────────────────────────────────────┘
```

---

## 🛠️ Tech Stack

| Technology | Purpose | Version |
|------------|---------|---------|
| **Java** | Core programming language | 17+ |
| **Java Swing** | GUI framework | Built-in |
| **FlatLaf** | Modern Look and Feel theme | 3.4.1 / 3.5.1 |
| **MySQL** | Relational database | 8.x / 9.x |
| **MySQL Connector/J** | JDBC driver for MySQL | 8.4.0 / 9.0.0 |
| **JasperReports** | PDF report generation | 7.0.0 |
| **JFreeChart** | Data visualization (charts) | 1.0.19 |
| **JavaMail API** | Email sending/receiving | 1.6.2 |
| **iText / OpenPDF** | PDF document manipulation | 2.1.7 / 2.0.3 |
| **MigLayout** | Advanced Swing layout manager | 4.0 |
| **Jackson** | JSON/XML data binding | 2.17.2 |
| **Gson** | JSON parsing | 2.9.0 |
| **JCalendar / DateChooser** | Date picker components | 1.4 |
| **TimingFramework** | Animation framework | 0.55 |
| **JSVG** | SVG rendering support | 1.4.0 |
| **Apache PDFBox** | PDF processing | 3.0.3 |
| **Swing Toast Notifications** | Toast notification alerts | 1.0.3 |
| **Apache NetBeans** | IDE | Latest |

---

## 📂 Project Structure

```
Company Management System/
│
├── 📁 src/                                    # Source code root
│   ├── 📁 com/nex/cms/                        # Main application package
│   │   ├── 📁 components/                     # Custom UI components
│   │   │   ├── Button.java                    # Custom rounded button
│   │   │   ├── TextField.java                 # Custom styled text field
│   │   │   ├── PasswordField.java             # Custom password field
│   │   │   ├── PanelRound.java                # Rounded panel component
│   │   │   ├── MaterialTabbed.java            # Material-style tab pane
│   │   │   ├── RippleEffect.java              # Ripple animation effect
│   │   │   ├── ScrollBarWin11UI.java           # Windows 11 style scrollbar
│   │   │   ├── ScrollPaneWin11.java            # Windows 11 scroll pane
│   │   │   ├── ShadowRenderer.java            # Shadow rendering utility
│   │   │   ├── GraphicsUtilities.java         # Graphics helper methods
│   │   │   └── PolygonCorner.java             # Polygon corner rendering
│   │   │
│   │   ├── 📁 connection/                     # Database layer
│   │   │   └── MySQL.java                     # MySQL connection & queries
│   │   │
│   │   ├── 📁 frame/                          # Application frames (windows)
│   │   │   ├── login.java                     # Login window
│   │   │   ├── home.java                      # Main home window with navigation
│   │   │   ├── salesMarketing.java            # Sales & Marketing frame
│   │   │   ├── hrm_Attendance.java            # HRM Attendance frame
│   │   │   ├── 📁 customer/                   # Customer-related frames
│   │   │   │   └── customerReg.java           # Customer registration
│   │   │   ├── 📁 hrm/                        # HRM-related frames
│   │   │   │   └── selectEmployee.java        # Employee selection
│   │   │   └── 📁 inventory/                  # Inventory-related frames
│   │   │       ├── CompanyRegistration.java    # Company registration
│   │   │       ├── SupplierRegistration.java   # Supplier registration
│   │   │       └── fstock.java                # Stock frame
│   │   │
│   │   ├── 📁 model/                          # Data models & utilities
│   │   │   ├── User.java                      # User session model
│   │   │   ├── Menu.java                      # Navigation menu component
│   │   │   ├── MenuItem.java                  # Individual menu item
│   │   │   ├── MenuAnimation.java             # Menu animation handler
│   │   │   ├── MenuEvent.java                 # Menu event interface
│   │   │   ├── InvoiceItem.java               # Invoice line item model
│   │   │   ├── grnItem.java                   # GRN line item model
│   │   │   ├── LoginDetails.java              # Login credentials model
│   │   │   ├── Validations.java               # Input validation utilities
│   │   │   └── tableAlign.java                # Table alignment renderer
│   │   │
│   │   └── 📁 panel/                          # Application panels (views)
│   │       ├── DashBord.java                  # Main dashboard with charts
│   │       ├── Header.java                    # Top header bar
│   │       ├── 📁 accounting/                 # Accounting module panels
│   │       │   ├── Accounts_Managament.java
│   │       │   ├── Transactions_Management.java
│   │       │   ├── Budgets_Management.java
│   │       │   └── Loans_Management.java
│   │       ├── 📁 Inventory/                  # Inventory module panels
│   │       │   ├── stock.java
│   │       │   ├── property.java
│   │       │   ├── GRN.java
│   │       │   └── Invoice.java
│   │       ├── 📁 hrm/                        # HRM module panels
│   │       │   ├── employee.java
│   │       │   ├── employeeLeave.java
│   │       │   ├── employeeTraning.java
│   │       │   ├── attendance.java
│   │       │   └── payrollMng.java
│   │       ├── 📁 marketing/                  # Marketing module panels
│   │       │   ├── digitalMarketing.java
│   │       │   ├── marketReaserch.java
│   │       │   ├── advertising.java
│   │       │   └── socialMediaManagement.java
│   │       ├── 📁 communication/              # Communication module panels
│   │       │   ├── Internal.java
│   │       │   ├── extranal.java
│   │       │   └── mailBox.java
│   │       ├── 📁 cusutomer/                  # Customer module panels
│   │       │   └── customerReg.java
│   │       └── 📁 reporting/                  # Reporting module panels
│   │           ├── report.java
│   │           ├── attendance.java
│   │           └── events.java
│   │
│   ├── 📁 chart/                              # Chart components
│   │   ├── PolarAreaChart.java                # Polar area chart widget
│   │   ├── ModelPolarAreaChart.java           # Chart data model
│   │   └── PolarAreaLabel.java                # Chart label component
│   │
│   ├── 📁 component/                          # Additional UI components
│   │   ├── Background.java                    # Background panel
│   │   └── RoundButton.java                   # Rounded button
│   │
│   ├── 📁 mainPanel/                          # Panel utilities
│   │   ├── PanelBackground.java               # Background panel
│   │   ├── PanelShadow.java                   # Shadow panel
│   │   ├── ShadowType.java                    # Shadow type enum
│   │   ├── SQLException.java                  # Custom SQL exception
│   │   ├── 📁 scrollbar/                      # Custom scrollbar
│   │   └── 📁 shadow/                         # Shadow rendering
│   │
│   ├── 📁 reports/                            # JasperReports templates (.jasper)
│   │   ├── EmpReg_Repo.jasper                 # Employee Registration Report
│   │   ├── EmpAtt_Repot.jasper                # Employee Attendance Report
│   │   ├── EmpLeav_Repo.jasper                # Employee Leave Report
│   │   ├── EmpSal_Repo.jasper                 # Employee Salary Report
│   │   ├── EmpTran_Repo.jasper                # Employee Training Report
│   │   ├── AccountManagement.jasper           # Account Management Report
│   │   ├── TransactionManagement.jasper       # Transaction Report
│   │   ├── BMhrm.jasper                       # Budget Management - HRM
│   │   ├── BMinventry.jasper                  # Budget Management - Inventory
│   │   ├── BMloan.jasper                      # Budget Management - Loans
│   │   ├── BMmarketing.jasper                 # Budget Management - Marketing
│   │   ├── inv_cureentStock.jasper            # Current Stock Report
│   │   ├── inv_cureentProperty.jasper         # Current Property Report
│   │   ├── inv_grn.jasper                     # GRN Report
│   │   ├── inv_invoice.jasper                 # Invoice Report
│   │   ├── mar_Social.jasper                  # Social Media Report
│   │   ├── mar_promo.jasper                   # Promotions Report
│   │   └── ... (30+ report templates)
│   │
│   ├── 📁 Source Image/                       # Application icons & assets
│   └── 📁 images/                             # Project screenshots
│
├── 📁 lib/                                    # External JAR dependencies
├── 📁 build/                                  # Compiled classes
├── 📁 dist/                                   # Distribution (JAR)
├── 📁 generated_reports/                      # Generated PDF reports output
├── 📁 Customer Invoices/                      # Customer invoice PDFs
├── 📁 nbproject/                              # NetBeans project configuration
├── 📁 test/                                   # Unit tests (placeholder)
├── build.xml                                  # Ant build script
├── CMS.log                                    # Application log file
└── README.md                                  # This file
```

---

## ⚙️ Prerequisites

Before running the application, ensure you have the following installed:

| Requirement | Minimum Version | Download |
|-------------|----------------|----------|
| **Java JDK** | 17 or higher | [Download JDK](https://www.oracle.com/java/technologies/downloads/) |
| **MySQL Server** | 8.0+ | [Download MySQL](https://dev.mysql.com/downloads/mysql/) |
| **Apache NetBeans** | 17+ (recommended) | [Download NetBeans](https://netbeans.apache.org/download/) |

---

## 🗄️ Database Setup

1. **Start MySQL Server** and create the database:

```sql
CREATE DATABASE `c.m.system`;
USE `c.m.system`;
```

2. **Create required tables** — The application uses the following core tables (create based on your schema):

```sql
-- Example: Employee Table
CREATE TABLE employee (
    employee_id VARCHAR(20) PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(150),
    mobile VARCHAR(15),
    gender VARCHAR(10),
    job_roll VARCHAR(100),
    department VARCHAR(100),
    username VARCHAR(50),
    password VARCHAR(100),
    line1 VARCHAR(200),
    line2 VARCHAR(200),
    city VARCHAR(100)
);

-- Example: Invoice Table
CREATE TABLE invoice (
    invoice_id VARCHAR(20) PRIMARY KEY,
    date DATE,
    customer_id VARCHAR(20),
    paid_amount DOUBLE,
    total DOUBLE
);

-- Example: GRN Table
CREATE TABLE grn (
    grn_id VARCHAR(20) PRIMARY KEY,
    date DATE,
    supplier_id VARCHAR(20),
    WePaidAmount DOUBLE,
    total DOUBLE
);

-- Additional tables: attendance, leave, training, payroll, 
-- accounts, transactions, budgets, loans, customers, 
-- stock, property, marketing, etc.
```

3. **Update database credentials** in `src/com/nex/cms/connection/MySQL.java`:

```java
connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/c.m.system", 
    "root",           // ← Your MySQL username
    "your_password"   // ← Your MySQL password
);
```

---

## 🚀 Installation & Setup

### Option 1: Using NetBeans IDE (Recommended)

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/Company-Management-System.git
   ```

2. **Open in NetBeans:**
   - Launch Apache NetBeans IDE
   - Go to `File` → `Open Project`
   - Navigate to the cloned `Company Management System` directory
   - Select the project and click `Open`

3. **Resolve dependencies:**
   - All required JAR files are included in the `lib/` folder
   - NetBeans should automatically detect and configure them
   - If not, right-click the project → `Properties` → `Libraries` → Add JARs from `lib/`

4. **Configure the database:**
   - Follow the [Database Setup](#-database-setup) section above
   - Update MySQL credentials in `MySQL.java`

5. **Build and Run:**
   - Press `F6` or click the **Run** button
   - The Login window will appear

### Option 2: Using Command Line

1. **Compile the project:**
   ```bash
   cd "Company Management System"
   ant compile
   ```

2. **Run the application:**
   ```bash
   ant run
   ```

   Or run the JAR directly from `dist/`:
   ```bash
   java -jar dist/CompanyManagementSystem.jar
   ```

---

## 📘 Usage Guide

### 🔐 Login
1. Launch the application — the **NexOra CMS Login** window appears
2. Enter your **Username** and **Password**
3. Click **Login** to access the system
4. Access to modules is determined by your department role

### 🧭 Navigation
- The **left sidebar menu** provides access to all modules
- Click a module header to expand its sub-menu items
- The **Dashboard** is loaded by default on login
- The **Header** bar shows the current time, date, and logout button

### 📊 Dashboard
- View **Income Overview** bar chart (monthly revenue)
- Analyze **GRN Trends** via line chart
- Check key **data summary boxes**
- View **polar area charts** for distribution analysis

### 🖨️ Generating Reports
1. Navigate to **Reporting & Analytics** → **Report**
2. Select the desired report category
3. Click **Generate** to create a PDF report
4. Reports are saved in the `generated_reports/` directory

### 📧 Sending Emails
1. Go to **Communication Tools** → **External Tools**
2. Fill in recipient email, subject, and message body
3. Click **Send** to dispatch the email

---

## 📦 Module Details

### Module Access by Department

| Module | HR Dept | Finance & Acct | Inventory | Marketing | Admin |
|--------|:-------:|:-------------:|:---------:|:---------:|:-----:|
| Dashboard | ✅ | ✅ | ✅ | ✅ | ✅ |
| Human Resources | ✅ | ❌ | ✅ | ❌ | ✅ |
| Accounting | ❌ | ✅ | ❌ | ❌ | ✅ |
| Customer Relationship | ✅ | ✅ | ✅ | ✅ | ✅ |
| Marketing | ❌ | ❌ | ❌ | ✅ | ✅ |
| Inventory | ❌ | ❌ | ✅ | ❌ | ✅ |
| Communication | ✅ | ✅ | ✅ | ✅ | ✅ |
| Reporting | ✅ | ✅ | ✅ | ✅ | ✅ |

---

## 📚 Libraries & Dependencies

All libraries are pre-packaged in the `lib/` directory:

| Library | File | Purpose |
|---------|------|---------|
| FlatLaf | `flatlaf-3.5.1.jar` | Modern Look & Feel theme |
| FlatLaf Extras | `flatlaf-extras-3.4.1.jar` | Additional FlatLaf utilities |
| MySQL Connector | `mysql-connector-j-9.0.0.jar` | JDBC database driver |
| JasperReports | `jasperreports-7.0.0.jar` | Report generation engine |
| JasperReports PDF | `jasperreports-pdf-7.0.0.jar` | PDF export for reports |
| JasperReports Fonts | `jasperreports-fonts-7.0.0.jar` | Font bundles for reports |
| JasperReports Excel | `jasperreports-excel-poi-7.0.0.jar` | Excel export for reports |
| JFreeChart | `jfreechart-1.0.19.jar` | Chart & graph rendering |
| JavaMail | `javax.mail-1.6.2.jar` | Email sending/receiving |
| Jackson Databind | `jackson-databind-2.17.2.jar` | JSON data binding |
| Jackson XML | `jackson-dataformat-xml-2.17.2.jar` | XML data format support |
| Gson | `gson-2.9.0.jar` | JSON serialization |
| iText | `itext-2.1.7.jar` | PDF document creation |
| OpenPDF | `openpdf-2.0.3.jar` | Open-source PDF library |
| Apache PDFBox | `pdfbox-3.0.3.jar` | PDF document processing |
| MigLayout | `miglayout-4.0.jar` | Advanced layout manager |
| JCalendar | `jcalendar-1.4.jar` | Date picker widget |
| DateChooser | `DateChooser.jar` | Date chooser component |
| JSVG | `jsvg-1.4.0.jar` | SVG rendering support |
| TimingFramework | `TimingFramework-0.55.jar` | Animation framework |
| Toast Notifications | `swing-toast-notifications-1.0.3.jar` | Toast alert popups |
| Time Picker | `swing-time-picker.jar` | Time picker widget |
| Java Geo Chart | `java-geo-chart.jar` | Geographic chart component |
| Commons BeanUtils | `commons-beanutils-1.9.4.jar` | JavaBean utilities |
| Commons Collections | `commons-collections4-4.4.jar` | Collection utilities |
| Commons Digester | `commons-digester-2.1.jar` | XML parsing |
| Commons Logging | `commons-logging-1.2.jar` | Logging abstraction |

---

## 👨‍💻 Contributors

This project was developed as a **Group Project** by the development team at NexOra.

> _If you'd like to add contributor names, please update this section with team member details._

| Role | Name | Contribution |
|------|------|-------------|
| Team Lead | — | Project Architecture & Coordination |
| Developer | — | HRM Module |
| Developer | — | Accounting Module |
| Developer | — | Inventory Module |
| Developer | — | Marketing & Communication Module |
| Developer | — | Reporting & Analytics Module |

---

## 📄 License

This project is developed for educational and internal use. All rights reserved.

---

## 🤝 Contributing

1. **Fork** the repository
2. **Create** your feature branch (`git checkout -b feature/AmazingFeature`)
3. **Commit** your changes (`git commit -m 'Add some AmazingFeature'`)
4. **Push** to the branch (`git push origin feature/AmazingFeature`)
5. **Open** a Pull Request

---

## 📞 Support

For any issues or questions regarding the project setup, please:

- Open an **Issue** on the repository
- Contact the development team
- Check the `CMS.log` file for application error logs

---

<p align="center">
  <strong>Built with ❤️ by the NexOra Development Team</strong>
</p>

<p align="center">
  <sub>© 2025 Mega Mart Z — Company Management System. All rights reserved.</sub>
</p>
