package com.ticketmaster.web;

import com.ticketmaster.model.Artist;
import com.ticketmaster.model.Genre;
import com.ticketmaster.service.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public String getArtistPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Artist> artists = this.artistService.listAll();
        Genre[] genres = Genre.values();
        model.addAttribute("artists", artists);
        model.addAttribute("genres", genres);
        model.addAttribute("bodyContent", "listArtists");
        return "masterTemplate";
    }

    @GetMapping("/add-artist")
    public String addArtistPage(Model model) {
        List<Artist> artists = this.artistService.listAll();
        Genre[] genres = Genre.values();
        model.addAttribute("artists", artists);
        model.addAttribute("genres", genres);
        model.addAttribute("bodyContent", "formArtist");

        return "masterTemplate";
    }

    @PostMapping("/add-artist")
    public String createArtist(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String url,
            @RequestParam String description,
            @RequestParam Genre genre) {


        this.artistService.create(name, url, description, genre);


        return "redirect:/artists";
    }

}

