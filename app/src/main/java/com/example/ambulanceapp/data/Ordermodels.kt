package com.example.ambulanceapp.data

// Enums
enum class OrderStatus { COMPLETED, CANCELLED, ONGOING }

// Models
data class TimelineEvent(
    val time: String,
    val label: String,
    val isDone: Boolean
)

data class AmbulanceOrder(
    val id: String,
    val serviceType: String,
    val date: String,
    val time: String,
    val hospital: String,
    val patientName: String,
    val patientAge: String,
    val condition: String,
    val complaint: String,
    val companion: String,
    val phone: String,
    val pickupAddress: String,
    val driverName: String,
    val driverPhone: String,
    val vehiclePlate: String,
    val distanceKm: String,
    val durationMin: String,
    val status: OrderStatus,
    val timeline: List<TimelineEvent>
)

// Sample Data
val sampleOrders = listOf(
    AmbulanceOrder(
        id            = "#AMB-0042",
        serviceType   = "Non-Emergency",
        date          = "Tuesday, 27 June 2026",
        time          = "09:30",
        hospital      = "RS Sardjito",
        patientName   = "Budi Santoso",
        patientAge    = "45",
        condition     = "Stable",
        complaint     = "Chest discomfort after physical activity",
        companion     = "Sari Santoso",
        phone         = "+62 812-3456-7890",
        pickupAddress = "Jl. Kaliurang No. 12, Ngaglik, Sleman",
        driverName    = "Maulana Malik",
        driverPhone   = "+62 857-0011-2233",
        vehiclePlate  = "AB 1234 CD",
        distanceKm    = "7.2 km",
        durationMin   = "18 min",
        status        = OrderStatus.COMPLETED,
        timeline      = listOf(
            TimelineEvent("09:30", "Order Placed",         isDone = true),
            TimelineEvent("09:33", "Driver Assigned",      isDone = true),
            TimelineEvent("09:41", "Ambulance En Route",   isDone = true),
            TimelineEvent("09:48", "Arrived at Pickup",    isDone = true),
            TimelineEvent("10:06", "Arrived at Hospital",  isDone = true)
        )
    ),
    AmbulanceOrder(
        id            = "#AMB-0041",
        serviceType   = "Emergency",
        date          = "Monday, 2 Jan 2025",
        time          = "14:15",
        hospital      = "RS PKU Muhammadiyah",
        patientName   = "Siti Rahayu",
        patientAge    = "32",
        condition     = "Critical",
        complaint     = "Difficulty breathing, suspected asthma attack",
        companion     = "Andi Rahayu",
        phone         = "+62 813-9988-7766",
        pickupAddress = "Jl. Gejayan No. 5, Depok, Sleman",
        driverName    = "-",
        driverPhone   = "-",
        vehiclePlate  = "-",
        distanceKm    = "-",
        durationMin   = "-",
        status        = OrderStatus.CANCELLED,
        timeline      = listOf(
            TimelineEvent("14:15", "Order Placed",    isDone = true),
            TimelineEvent("14:17", "Order Cancelled", isDone = true),
            TimelineEvent("-",     "Driver Assigned", isDone = false),
            TimelineEvent("-",     "En Route",        isDone = false),
            TimelineEvent("-",     "Completed",       isDone = false)
        )
    ),
    AmbulanceOrder(
        id            = "#AMB-0040",
        serviceType   = "Schedule",
        date          = "Sunday, 1 Jan 2025",
        time          = "08:00",
        hospital      = "RS Bethesda",
        patientName   = "Ahmad Fauzi",
        patientAge    = "60",
        condition     = "Stable",
        complaint     = "Routine hospital transfer for dialysis",
        companion     = "Nina Fauzi",
        phone         = "+62 821-5544-3322",
        pickupAddress = "Jl. Magelang No. 88, Mlati, Sleman",
        driverName    = "Bowo Susanto",
        driverPhone   = "+62 856-7788-9900",
        vehiclePlate  = "AB 5678 EF",
        distanceKm    = "12.4 km",
        durationMin   = "27 min",
        status        = OrderStatus.COMPLETED,
        timeline      = listOf(
            TimelineEvent("08:00", "Order Placed",        isDone = true),
            TimelineEvent("08:02", "Driver Assigned",     isDone = true),
            TimelineEvent("08:14", "Ambulance En Route",  isDone = true),
            TimelineEvent("08:22", "Arrived at Pickup",   isDone = true),
            TimelineEvent("08:49", "Arrived at Hospital", isDone = true)
        )
    ),
    AmbulanceOrder(
        id            = "#AMB-0039",
        serviceType   = "Corpse",
        date          = "Saturday, 08 June 2026",
        time          = "11:45",
        hospital      = "RS Panti Rapih",
        patientName   = "Dewi Kusuma",
        patientAge    = "28",
        condition     = "Stable",
        complaint     = "Post-surgery follow-up transport",
        companion     = "Rudi Kusuma",
        phone         = "+62 819-6677-8899",
        pickupAddress = "Jl. Cendana No. 3, Gondokusuman, Yogyakarta",
        driverName    = "Supriyadi",
        driverPhone   = "+62 858-1122-3344",
        vehiclePlate  = "AB 9012 GH",
        distanceKm    = "3.8 km",
        durationMin   = "12 min",
        status        = OrderStatus.ONGOING,
        timeline      = listOf(
            TimelineEvent("11:45", "Order Placed",       isDone = true),
            TimelineEvent("11:47", "Driver Assigned",    isDone = true),
            TimelineEvent("11:55", "Ambulance En Route", isDone = true),
            TimelineEvent("-",     "Arrived at Pickup",  isDone = false),
            TimelineEvent("-",     "Arrived at Hospital",isDone = false)
        )
    ),
    AmbulanceOrder(
        id            = "#AMB-0038",
        serviceType   = "Emergency",
        date          = "Friday, 09 May 2026",
        time          = "22:05",
        hospital      = "RS JIH",
        patientName   = "Riko Pratama",
        patientAge    = "19",
        condition     = "Critical",
        complaint     = "Head injury from traffic accident",
        companion     = "Dian Pratama",
        phone         = "+62 822-3344-5566",
        pickupAddress = "Jl. Ring Road Utara, Ngemplak, Sleman",
        driverName    = "Joko Anwar",
        driverPhone   = "+62 853-9900-1122",
        vehiclePlate  = "AB 3456 IJ",
        distanceKm    = "9.1 km",
        durationMin   = "21 min",
        status        = OrderStatus.COMPLETED,
        timeline      = listOf(
            TimelineEvent("22:05", "Order Placed",        isDone = true),
            TimelineEvent("22:07", "Driver Assigned",     isDone = true),
            TimelineEvent("22:13", "Ambulance En Route",  isDone = true),
            TimelineEvent("22:19", "Arrived at Pickup",   isDone = true),
            TimelineEvent("22:40", "Arrived at Hospital", isDone = true)
        )
    )
)