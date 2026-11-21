#version 450 core

in vec3 pass_position;
in vec3 pass_normal;

out vec4 out_Colour;

uniform vec4 tintColour;
uniform vec3 lightPos;

void main(){
        float lt_ambient = 0.1;
        float lt_direct = 1;
        vec3 lt_direct_dir = normalize(lightPos-pass_position);
        vec3 colour = vec3(tintColour.r, tintColour.g, tintColour.b);
        vec3 fragment_colour=(lt_ambient+(lt_direct*dot(lt_direct_dir,-pass_normal))*colour);
        out_Colour = vec4(fragment_colour.r, fragment_colour.g, fragment_colour.b, tintColour.a);
}