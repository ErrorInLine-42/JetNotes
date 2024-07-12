package com.example.jetnotes.data

import com.example.jetnotes.model.Note

class NotesDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "Meeting with Marketing Team", description = "Discuss the upcoming product launch strategies, review the marketing plan, and assign tasks to team members."),
            Note(title = "Project Deadline Reminder", description = "Reminder to submit the final project report by end of the week. Ensure all sections are thoroughly reviewed."),
            Note(title = "Client Feedback Session", description = "Gather feedback from clients about the recent software update, address concerns, and document suggestions."),
            Note(title = "Team Building Activity Ideas", description = "Brainstorm and compile a list of team-building activities for the next quarter to enhance team collaboration."),
            Note(title = "Quarterly Financial Report", description = "Prepare the financial report for the last quarter, including income, expenses, and profitability analysis."),
            Note(title = "Code Review Meeting", description = "Schedule a meeting to review the latest code changes, discuss improvements, and identify potential issues."),
            Note(title = "Team Lunch Outing", description = "Organize a team lunch outing to celebrate the successful completion of the recent project."),
            Note(title = "New Hire Orientation", description = "Conduct orientation for the new hires, introduce them to the team, and provide an overview of the company policies."),
            Note(title = "Server Maintenance", description = "Plan and execute the maintenance of the company servers to ensure smooth operation and security."),
            Note(title = "Budget Planning Session", description = "Hold a session to plan the budget for the next fiscal year, including projected income, expenses, and savings.")
        )
    }
}