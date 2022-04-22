package com.ticketmaster.web;

import com.ticketmaster.model.Artist;
import com.ticketmaster.model.Event;
import com.ticketmaster.model.Venue;
import com.ticketmaster.service.ArtistService;
import com.ticketmaster.service.EventService;
import com.ticketmaster.service.VenueService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final ArtistService artistService;
    private final VenueService venueService;

    public EventController(EventService eventService, ArtistService artistService, VenueService venueService) {
        this.eventService = eventService;
        this.artistService = artistService;
        this.venueService = venueService;
    }


    @GetMapping
    public String getEventPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Event> events = this.eventService.findAll();
        model.addAttribute("events", events);
        model.addAttribute("bodyContent", "listEvents");
        return "masterTemplate";
    }

    @GetMapping("/show-event/{id}")
    public String showEventPage(@PathVariable Long id, Model model) {
        Event event = this.eventService.findById(id);
        if (event != null) {
            model.addAttribute("event", event);
            model.addAttribute("bodyContent", "showEvent");
            return "masterTemplate";
        }
        return "redirect:/events?error=EventNotFound";
    }


    @GetMapping("/edit-form/{id}")
    public String editEventPage(@PathVariable Long id, Model model) {
        Event event = this.eventService.findById(id);
        if (event != null) {
            List<Artist> artists = this.artistService.listAll();
            List<Venue> venues = this.venueService.listAll();
            model.addAttribute("artists", artists);
            model.addAttribute("venues", venues);
            model.addAttribute("event", event);
            model.addAttribute("bodyContent", "formEvent");

            return "masterTemplate";
        }
        return "redirect:/events?error=EventNotFound";
    }


    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add-form")
    public String addEventPage(Model model) {
        List<Artist> artists = this.artistService.listAll();
        List<Venue> venues = this.venueService.listAll();
        model.addAttribute("artists", artists);
        model.addAttribute("venues", venues);
        model.addAttribute("bodyContent", "formEvent");

        return "masterTemplate";
    }



    @PostMapping("/add")
    public String createEvent(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam Float price,
            @RequestParam Float duration,
            @RequestParam String url,
            @RequestParam String description,
            @RequestParam List<Long> artistsId,
            @RequestParam Long venue) {

        if (id == null) {
            this.eventService.create(date, name, duration, price, url, description, artistsId, venue);
        } else {
            this.eventService.update(id, date, name, duration, price, url, description, artistsId, venue);
        }
        return "redirect:/events";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.eventService.delete(id);
        return "redirect:/events";
    }
    /*@DeleteMapping("/delete/show-event/{id}")
    public String deleteEvent(@PathVariable Long id) {
        this.eventService.deleteById(id);
        return "redirect:/events";
    }

     */



    /*@PostMapping("/events/{id}")
    public String updateEvent(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam Float price,
            @RequestParam Float duration,
            @RequestParam Long category,
            @RequestParam String url,
            @RequestParam List<Long> artistsId,
            @RequestParam Long venue){
        this.eventService.update(id,date,name,duration,price,url,artistsId,venue);
        return "redirect:/events";
    }*/
}


